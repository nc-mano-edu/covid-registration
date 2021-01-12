package com.edu.mano.covidregistration.controller;

import com.edu.mano.covidregistration.domain.Roles;
import com.edu.mano.covidregistration.domain.UserRoles;
import com.edu.mano.covidregistration.service.UserRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/UserRoles")
public class UserRolesController {

    @Autowired
    public UserRolesService userRolesService;

    @GetMapping("/getUserRoles")
    public List<UserRoles> getAllUserRoles(){

        return userRolesService.findAllUserRoles();
    }

    @PostMapping("/makeUserRole")
    public ResponseEntity<String> createUserRole(@RequestBody UserRoles userRole){

        return userRolesService.createUserRole(userRole);
    }

    @PutMapping("/editUserRole")
    public ResponseEntity<String> updateUserRole(@RequestBody UserRoles userRole){

        return userRolesService.updateUserRole(userRole);
    }

    @DeleteMapping("/deleteUserRole/{id}")
    public ResponseEntity<String> deleteUserRole(@PathVariable long id){

        return userRolesService.deleteUserRole(id);
    }

}
