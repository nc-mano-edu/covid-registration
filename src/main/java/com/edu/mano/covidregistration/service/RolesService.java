package com.edu.mano.covidregistration.service;

import com.edu.mano.covidregistration.domain.Roles;
import com.edu.mano.covidregistration.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesService {

    @Autowired
    public RolesRepository rolesRepository;

    public List<Roles> findAllRoles(){

        return rolesRepository.findAll();
    }

    public Roles findRoleById(long id){

        return rolesRepository.findById(id);
    }

    public ResponseEntity<String> createRole(Roles role){

        List<Roles> existingRoles = findAllRoles();

        for (Roles selectedRole : existingRoles){

            if (selectedRole.getRoleName().equals(role.getRoleName()) && selectedRole.getDescription().equals(role.getDescription())){
                return ResponseEntity.badRequest().build();
            }
        }

        rolesRepository.save(role);

        return ResponseEntity.ok("Successfully created a role");
//        if(!existingRoles.contains(role)){
//
//            rolesRepository.save(role);
//
//            return ResponseEntity.ok("Successfully created a role");
//
//        }else {
//
//            return ResponseEntity.badRequest().build();
//        }

    }

    public ResponseEntity<String> updateRole(Roles role){

        Roles desiredRole = rolesRepository.findById(role.getRoleId());

        if(!desiredRole.equals(role)){


            if (role.getDescription() != null){

                desiredRole.setDescription(role.getDescription());
            }

            if (role.getRoleName() != null){

                desiredRole.setRoleName(role.getRoleName());
            }

            if(role.obtainListOfUserRoles() != null){

                desiredRole.setUserRoleId(role.obtainListOfUserRoles());
            }

            rolesRepository.save(desiredRole);

            return ResponseEntity.ok("Successfully edited a role");

        }else {

            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<String> deleteRole(long id) {

        try {

            rolesRepository.deleteById(id);

            return ResponseEntity.ok("Successfully deleted role");

        }catch (EmptyResultDataAccessException e){

            return ResponseEntity.badRequest().build();
        }
    }

}
