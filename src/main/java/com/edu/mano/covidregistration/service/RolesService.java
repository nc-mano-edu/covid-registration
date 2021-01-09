package com.edu.mano.covidregistration.service;

import com.edu.mano.covidregistration.domain.Roles;
import com.edu.mano.covidregistration.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesService {

    @Autowired
    public RolesRepository rolesRepository;

    public List<Roles> findAllUserRoles(){

        return rolesRepository.findAll();
    }

    public void createUserRole(Roles role){

        rolesRepository.save(role);
    }

    public void update(Roles role){

        Roles desiredRole = rolesRepository.findById(role.getRolesId());

        if(desiredRole.getRolesId() == role.getRolesId()){

            rolesRepository.save(role);
        }
    }

    public void delete(long id){

        rolesRepository.deleteById(id);
    }

}
