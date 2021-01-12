package com.edu.mano.covidregistration.service;

import com.edu.mano.covidregistration.domain.User;
import com.edu.mano.covidregistration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public List<User> findAll(){

        return userRepository.findAll();
    }

    public User find (long id){

       return userRepository.findById(id);
    }

    public User saveUser(User user){

        return userRepository.save(user);
    }
}
