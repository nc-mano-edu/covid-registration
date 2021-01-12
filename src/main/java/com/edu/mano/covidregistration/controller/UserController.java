package com.edu.mano.covidregistration.controller;

import com.edu.mano.covidregistration.domain.User;
import com.edu.mano.covidregistration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/User")
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping("/getUsers")
    public List<User> getAllUsers(){

        return userService.findAll();
    }

    @PostMapping("/makeUser")
    public ResponseEntity<String> postNewUser(@RequestBody User user){

        userService.saveUser(user);

        return ResponseEntity.ok("Added a new user");
    }
}
