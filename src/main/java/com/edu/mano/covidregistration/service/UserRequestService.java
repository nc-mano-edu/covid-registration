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

    public UserRequest saveUserRequest(UserRequest symptom) {
        return userRequestRepository.save(symptom);
    }

    public void  deleteUserRequest(Long id) {
        userRequestRepository.deleteById(id);
    }


}
