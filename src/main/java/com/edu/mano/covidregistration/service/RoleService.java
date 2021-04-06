package com.edu.mano.covidregistration.service;

import com.edu.mano.covidregistration.domain.Role;
import com.edu.mano.covidregistration.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotNull;
import java.util.List;
@Service
public class RoleService {


    public final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAllRoles(){
        return roleRepository.findAll();
    }

    public Role findRoleById(Long id){
        return roleRepository.findById(id).get();
    }

    public ResponseEntity<String> createRole(Role role){
//        List<Role> existingRoles = findAllRoles();
//
//        for (Role selectedRole : existingRoles){
//            if (selectedRole.getName().equals(role.getName()) && selectedRole.getDescription().equals(role.getDescription())){
//                return ResponseEntity.badRequest().build();
//            }
//        }
        roleRepository.save(role);

        return ResponseEntity.ok("Successfully created a role");

    }

    public ResponseEntity<String> updateRole(Role role, Long id) {
        Role desiredRole = findRoleById(id);

        if (role.getDescription() != null) {
            desiredRole.setDescription(role.getDescription());
        }

        if (role.getName() != null) {
            desiredRole.setName(role.getName());
        }

        roleRepository.save(desiredRole);

        return ResponseEntity.ok("Successfully edited a role");
    }

    public ResponseEntity<String> deleteRole(long id) {
        roleRepository.deleteById(id);

        return ResponseEntity.ok("Successfully deleted role");

    }

}
