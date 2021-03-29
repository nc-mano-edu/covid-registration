package com.edu.mano.covidregistration.controller;

import com.edu.mano.covidregistration.domain.Roles;
import com.edu.mano.covidregistration.domain.User;
import com.edu.mano.covidregistration.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(path = "/User")
public class UserController {

    private static Logger log = LoggerFactory.getLogger(RolesController.class);

    @Autowired
    public UserService userService;

    @GetMapping("/getUsers")
    public List<User> getAllUsers(){

        return userService.findAll();
    }

    @PostMapping("/makeUser")
    public ResponseEntity<String> postNewUser(@RequestBody @Valid User user){

            userService.saveUser(user);

            return ResponseEntity.ok("Added a new user");


    }

    @PutMapping("/editUser")
    public ResponseEntity<String> updateUser(@RequestBody @Valid User user){

            userService.updateUser(user);

            log.info(user + "role was edited");

            return ResponseEntity.ok("Edited a user with id" + user.getId());

    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id){

        log.info("user with id" + id + "was deleted");

        return userService.deleteUser(id);
    }


}
