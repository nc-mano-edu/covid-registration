package com.edu.mano.covidregistration.controller;

import com.edu.mano.covidregistration.domain.Roles;
import com.edu.mano.covidregistration.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/Roles")
public class RolesController {

    @Autowired
    public RolesService rolesService;

    @GetMapping("/getRoles")
    public List<Roles> getAllRoles(){

        return rolesService.findAllRoles();
    }

    @PostMapping("/makeRole")
    public ResponseEntity<String> createRole(@RequestBody Roles role){

        return rolesService.createRole(role);
    }

    @PutMapping("/editRole")
    public ResponseEntity<String> updateRole(@RequestBody Roles role){

       return rolesService.updateRole(role);
    }

    @DeleteMapping("/deleteRole/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable long id){

       return rolesService.deleteRole(id);
    }
}
