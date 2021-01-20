package com.edu.mano.covidregistration.controller;

import com.edu.mano.covidregistration.domain.Roles;
import com.edu.mano.covidregistration.service.RolesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/Roles")
public class RolesController {

    private static Logger log = LoggerFactory.getLogger(RolesController.class);

    @Autowired
    public RolesService rolesService;

    @GetMapping("/getRoles")
    public List<Roles> getAllRoles(){

        return rolesService.findAllRoles();
    }

    @PostMapping("/makeRole")
    public ResponseEntity<String> createRole(@RequestBody @Valid Roles role){

        log.info(role + "role was created");

        return rolesService.createRole(role);
    }

    @PutMapping("/editRole")
    public ResponseEntity<String> updateRole(@RequestBody @Valid Roles role){

        log.info(role + "role was edited");

       return rolesService.updateRole(role);
    }

    @DeleteMapping("/deleteRole/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable long id){

        log.info("role with id" + id + "was deleted");

       return rolesService.deleteRole(id);
    }
}
