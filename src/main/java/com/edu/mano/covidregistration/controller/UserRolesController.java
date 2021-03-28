package com.edu.mano.covidregistration.controller;

import com.edu.mano.covidregistration.domain.UserRoles;
import com.edu.mano.covidregistration.service.UserRolesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/UserRoles")
public class UserRolesController {

    private static Logger log = LoggerFactory.getLogger(UserRolesController.class);

    @Autowired
    public UserRolesService userRolesService;

    @GetMapping("/getUserRoles")
    public List<UserRoles> getAllUserRoles(){

        return userRolesService.findAllUserRoles();
    }

    @GetMapping("/getUserRoleById/{id}")
    public UserRoles getUserRoleById(@PathVariable Long id){

        return userRolesService.findUserRoleById(id);
    }

    @PostMapping("/makeUserRole")
    public ResponseEntity<String> createUserRole(@RequestBody @Valid UserRoles userRole){

        log.info("user with id" + userRole.obtainUserId() + "was assigned to role with id" + userRole.obtainRolesId());

        return userRolesService.createUserRole(userRole);
    }

    @PutMapping("/editUserRole")
    public ResponseEntity<String> updateUserRole(@RequestBody @Valid UserRoles userRole){

        log.info("user with id" + userRole.obtainUserId() + "was reassigned to role with id" + userRole.obtainRolesId());

        return userRolesService.updateUserRole(userRole);
    }

    @DeleteMapping("/deleteUserRole/{id}")
    public ResponseEntity<String> deleteUserRole(@PathVariable long id){

        log.info("user roles with id" + id + "was deleted");

        return userRolesService.deleteUserRole(id);
    }

}
