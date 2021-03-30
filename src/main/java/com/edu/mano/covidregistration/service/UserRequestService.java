package com.edu.mano.covidregistration.service;

import com.edu.mano.covidregistration.domain.UserRequest;
import com.edu.mano.covidregistration.repository.UserRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRequestService {

    @Autowired
    private UserRequestRepository userRequestRepository;

    public UserRequestService(UserRequestRepository userRequestRepository) {
        this.userRequestRepository = userRequestRepository;
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


        return findRequestByRequestId(request.getRequestId());
    }

    public void deleteUserRequest(Long id) {
        userRequestRepository.deleteById(id);
    }
}
