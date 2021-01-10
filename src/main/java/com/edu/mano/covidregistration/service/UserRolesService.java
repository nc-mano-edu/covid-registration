package com.edu.mano.covidregistration.service;

import com.edu.mano.covidregistration.domain.UserRoles;
import com.edu.mano.covidregistration.repository.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRolesService {

    @Autowired
    public UserRolesRepository userRolesRepository;

    public List<UserRoles> findAllUserRoles(){

       return userRolesRepository.findAll();
    }

    public void createUserRole(UserRoles userRole){

        userRolesRepository.save(userRole);
    }

//    public void update(UserRoles userRole){
//
//        UserRoles requiredUser = userRolesRepository.findById(userRole.getUserId());
//
//        if(requiredUser.getUserId() == userRole.getUserId()){
//
//            userRolesRepository.save(userRole);
//        }
//    }

    public void delete(long id){

        userRolesRepository.deleteById(id);
    }
}
