package com.edu.mano.covidregistration.service;

import com.edu.mano.covidregistration.domain.TaskInstance;
import com.edu.mano.covidregistration.domain.User;
import com.edu.mano.covidregistration.domain.UserRequest;
import com.edu.mano.covidregistration.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserRequestService userRequestService;

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository, UserRequestService userRequestService) {
        this.userRepository = userRepository;
        this.userRequestService = userRequestService;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User findUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<TaskInstance> findTasks(Long id) {
        User user = findUser(id);
        if (user == null) {
            return null;
        }

        List<TaskInstance> taskInstances = new ArrayList<>();

        List<UserRequest> userRequests = userRequestService.findRequestByUserId(id);
        userRequests.forEach(request -> {
            taskInstances.addAll(userRequestService.findTasks(request.getRequestId()));
        });

        return taskInstances;
    }

    public List<TaskInstance> findActiveTasks(Long id) {
        List<TaskInstance> tasks = findTasks(id);
        if (tasks == null) {
            return null;
        }
        return tasks.stream().filter(t -> t.getFinishedTime() == null).collect(Collectors.toList());
    }
}
