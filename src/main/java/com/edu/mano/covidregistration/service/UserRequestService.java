package com.edu.mano.covidregistration.service;

import com.edu.mano.covidregistration.domain.Task;
import com.edu.mano.covidregistration.domain.TaskInstance;
import com.edu.mano.covidregistration.domain.UserRequest;
import com.edu.mano.covidregistration.enums.TreatmentState;
import com.edu.mano.covidregistration.exception.baseExceptions.NotFoundException;
import com.edu.mano.covidregistration.repository.UserRequestRepository;
import com.edu.mano.covidregistration.tools.AppUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserRequestService {

    private final UserRequestRepository userRequestRepository;
    private final TaskService taskService;
    private final TaskInstanceService taskInstanceService;

    @Autowired
    public UserRequestService(UserRequestRepository userRequestRepository,
                              TaskService taskService,
                              TaskInstanceService taskInstanceService) {
        this.userRequestRepository = userRequestRepository;
        this.taskService = taskService;
        this.taskInstanceService = taskInstanceService;
    }


    public List<UserRequest> findAll() {
        return userRequestRepository.findAll();
    }

    public UserRequest findRequestByRequestId(Long id) {
        final UserRequest request = userRequestRepository.findUserRequestByRequestId(id);
        if (request == null) {
            throw new NotFoundException(UserRequest.class, id);
        }
        return request;
    }

    public List<UserRequest> findRequestByUserId(Long userId) {
        return userRequestRepository.findUserRequestsByUserId(userId);
    }

    @Transactional
    public UserRequest saveUserRequest(UserRequest request) {
        if (request.getTreatmentState() == null) {
            request.setTreatmentState(TreatmentState.STARTED);
        }

        userRequestRepository.save(request);

        List<Task> tasks = taskService.findAll();

        tasks.forEach(task -> {
            TaskInstance taskInstance = new TaskInstance();
            taskInstance.setTask(task);
            taskInstance.setRequest(request);
            taskInstance.setCreatedTime(AppUtility.getCurrentDate());
            taskInstanceService.add(taskInstance);
        });

        return findRequestByRequestId(request.getRequestId());
    }

    @Transactional
    public UserRequest updateUserRequest(Long id, UserRequest request) {
        findRequestByRequestId(id);
        request.setRequestId(id);
        return userRequestRepository.save(request);
    }

    public List<TaskInstance> findTasks(Long id) {
        return taskInstanceService.findByRequestId(id);
    }

    @Transactional
    public void deleteUserRequest(Long id) {
        userRequestRepository.deleteById(id);
    }

    public List<UserRequest> findActive() {
        return userRequestRepository.findAllByEndDateIsNull();
    }

    @Transactional
    public UserRequest fillDoctorRecommendations(Long id, String doctorRecommendations) {
        final UserRequest userRequest = findRequestByRequestId(id);
        if (userRequest.getEndDate() != null) {
            return userRequest;
        }
        userRequest.setDoctorRecommendations(doctorRecommendations);
        return userRequestRepository.save(userRequest);
    }

    @Transactional
    public UserRequest closeUserRequest(Long id, String doctorRecommendations) {
        final UserRequest userRequest = findRequestByRequestId(id);
        if (userRequest.getEndDate() != null) {
            return userRequest;
        }

        List<TaskInstance> tasks = findTasks(id);
        tasks.forEach(task -> taskInstanceService.terminate(task.getId()));

        userRequest.setTreatmentState(TreatmentState.FINISHED);
        userRequest.setEndDate(AppUtility.getCurrentDate());
        userRequest.setDoctorRecommendations(doctorRecommendations);

        return userRequestRepository.save(userRequest);
    }
}
