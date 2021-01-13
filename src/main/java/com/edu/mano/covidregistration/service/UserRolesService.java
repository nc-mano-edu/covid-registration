package com.edu.mano.covidregistration.service;

import com.edu.mano.covidregistration.domain.UserRoles;
import com.edu.mano.covidregistration.repository.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRolesService {

    @Autowired
    public UserRolesRepository userRolesRepository;

    public List<UserRoles> findAllUserRoles(){

       return userRolesRepository.findAll();
    }

    public UserRoles findUserRoleById(long id){

        return userRolesRepository.findById(id);
    }


    public ResponseEntity<String> createUserRole(UserRoles userRole){

        List<UserRoles> existingRoles = findAllUserRoles();

        for (UserRoles userRoles : existingRoles){

            if (userRoles.obtainUserId() == userRole.obtainUserId()){

                return ResponseEntity.badRequest().build();
            }
        }

        userRolesRepository.save(userRole);

        return ResponseEntity.ok("Successfully created an item");

    }

    public ResponseEntity<String> updateUserRole(UserRoles userRole){

        UserRoles requiredUser = userRolesRepository.findById(userRole.obtainUserId());

        if(!requiredUser.equals(userRole)){

            userRolesRepository.save(userRole);

            return ResponseEntity.ok("Successfully edited an item");

        }else {

            return ResponseEntity.badRequest().build();
        }

    }

    public ResponseEntity<String> deleteUserRole(long id){

        try {

            userRolesRepository.deleteById(id);

            return ResponseEntity.ok("Successfully deleted role");

        }catch (EmptyResultDataAccessException e){

            return ResponseEntity.badRequest().build();
        }

    }
}
