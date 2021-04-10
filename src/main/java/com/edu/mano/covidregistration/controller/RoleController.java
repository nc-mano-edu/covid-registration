package com.edu.mano.covidregistration.controller;

import com.edu.mano.covidregistration.domain.Role;
import com.edu.mano.covidregistration.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.edu.mano.covidregistration.CovidRegistrationApplication.ROLE_BASE_PREFIX;

@RestController
@RequestMapping(ROLE_BASE_PREFIX)
public class RoleController {
    private final RoleService roleService;

    @Autowired
    RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/all")
    public List<Role> getAllRoles() {
        return roleService.findAllRoles();
    }

    @GetMapping("/{id}")
    public Role getSpecificRole(@PathVariable Long id) {
        return roleService.findRoleById(id);
    }

    @PostMapping()
    public ResponseEntity<String> createRole(@RequestBody @Valid Role role) {
        return roleService.createRole(role);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRole(@RequestBody @Valid Role role, @PathVariable Long id) {
        return roleService.updateRole(role, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable long id) {
        return roleService.deleteRole(id);
    }
}
