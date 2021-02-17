package com.edu.mano.covidregistration.service;

import com.edu.mano.covidregistration.domain.Roles;
import com.edu.mano.covidregistration.domain.User;
import com.edu.mano.covidregistration.domain.UserRoles;
import com.edu.mano.covidregistration.repository.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRolesService {

    private final UserRolesRepository userRolesRepository;

    private final RolesService rolesService;

    private final UserService userService;

    @Autowired
    public UserRolesService(UserRolesRepository userRolesRepository, RolesService rolesService, UserService userService){

        this.userRolesRepository = userRolesRepository;

        this.rolesService = rolesService;

        this.userService = userService;
    }

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

        UserRoles requiredUserRoleId = userRolesRepository.findById(userRole.getUserRoleId());

        if(!requiredUserRoleId.equals(userRole)){

            List<Roles> requiredRoles = new ArrayList<>();

            List<User> savedUsers = new ArrayList<>();

            User requiredUser = userService.find(userRole.obtainUserId());

            savedUsers.add(requiredUser);

            for (Roles roles : userRole.obtainListOfRolesIds()){

                requiredRoles.add(rolesService.findRoleById(roles.getRoleId()));
            }

            userRole.setRoleId(requiredRoles);

            userRole.setUser(savedUsers);

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
