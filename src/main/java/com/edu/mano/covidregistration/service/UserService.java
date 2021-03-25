package com.edu.mano.covidregistration.service;

import com.edu.mano.covidregistration.domain.User;
import com.edu.mano.covidregistration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public List<User> findAll() {

        return userRepository.findAll();
    }

    public User findSpecificUser(long id) {

        return userRepository.findById(id).get();
    }

    public User saveUser(User user){

        return userRepository.save(user);
    }

    public ResponseEntity<String> updateUser(User user) {

        User desiredUser = userRepository.findById(user.getId()).get();

        if (!desiredUser.equals(user) && desiredUser != null) {


            if (user.getFirstName() != null) {

                desiredUser.setFirstName(user.getFirstName());
            }

            if (user.getMiddleName() != null) {

                desiredUser.setMiddleName(user.getMiddleName());
            }

            if (user.getLastName() != null) {

                desiredUser.setLastName(user.getLastName());
            }

            if (user.getDateOfBirth() != null) {
                
                desiredUser.setDateOfBirth(user.getDateOfBirth());
            }

            if (user.getInsuranceNumber() != null) {

                desiredUser.setInsuranceNumber(user.getInsuranceNumber());
            }

            if (user.getPhoneNumber() != null){

                desiredUser.setPhoneNumber(user.getPhoneNumber());
            }

            userRepository.save(desiredUser);

            return ResponseEntity.ok("Successfully edited a role");

        } else {

            return ResponseEntity.badRequest().build();
        }

    }

    public ResponseEntity<String> deleteUser(long id) {

        try {

            userRepository.deleteById(id);

            return ResponseEntity.ok("Successfully deleted role");

        } catch (EmptyResultDataAccessException e) {

            return ResponseEntity.badRequest().build();
        }
    }


}
