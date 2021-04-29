package com.edu.mano.covidregistration.controller;

import com.edu.mano.covidregistration.domain.User;
import com.edu.mano.covidregistration.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/registeruser")
    public User registerUser (@RequestBody User user) throws Exception {
        String tempEmail = user.getEmail();
        if (tempEmail != null && !"".equals(tempEmail)) {
            User clientObj  = registrationService.fetchUserByEmail(tempEmail);
            if (clientObj != null ) {
                throw new Exception("client with" +  tempEmail  + " is already exist ");
            }
        }

        User userObj = null;
        userObj = registrationService.saveUser(user);

        return userObj;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public User loginUser (@RequestBody User user) throws Exception {
        String tempEmail = user.getEmail();
        String tempPassword = user.getPassword();
        User userObj = null;

        if (tempEmail != null && tempPassword != null) {
            userObj = registrationService.fetchUserByEmailAndPassword(tempEmail, tempPassword);
        }
        if (userObj == null) {
            throw new Exception("bad credentials");
        }

        return  userObj;
    }
}
