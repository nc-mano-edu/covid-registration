package com.edu.mano.covidregistration.service;

import com.edu.mano.covidregistration.domain.Roles;
import com.edu.mano.covidregistration.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Profile("!test")
@Service
public class RolesService {


    public final RolesRepository rolesRepository;

    @Autowired
    public RolesService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public List<Roles> findAllRoles(){

        return rolesRepository.findAll();
    }

    public Roles findRoleById(Long id){

        return rolesRepository.findById(id).get();
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

    }

    public ResponseEntity<String> updateRole(Roles role, Long id){

        Roles desiredRole = findRoleById(id);

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
