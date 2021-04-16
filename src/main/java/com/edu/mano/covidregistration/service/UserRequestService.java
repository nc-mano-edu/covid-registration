package com.edu.mano.covidregistration.service;

import com.edu.mano.covidregistration.domain.Task;
import com.edu.mano.covidregistration.domain.TaskInstance;
import com.edu.mano.covidregistration.domain.UserRequest;
import com.edu.mano.covidregistration.repository.UserRequestRepository;
import com.edu.mano.covidregistration.tools.AppUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return userRequestRepository.findUserRequestByRequestId(id);
    }

    public List<UserRequest> findRequestByUserId(Long userId) {
        return userRequestRepository.findUserRequestsByUserId(userId);
    }

    public UserRequest saveUserRequest(UserRequest request) {
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

    public List<TaskInstance> findTasks(Long id) {
        return taskInstanceService.findByRequestId(id);
    }

    public void deleteUserRequest(Long id) {
        userRequestRepository.deleteById(id);
    }

    public List<UserRequest> findActive() {
        return userRequestRepository.findAllByEndDateIsNull();
    }
}
