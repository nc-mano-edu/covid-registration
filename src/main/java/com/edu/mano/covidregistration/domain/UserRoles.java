package com.edu.mano.covidregistration.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "USER_ROLES")
public class UserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_role_id")
    private long userRoleId;

    @OneToMany(cascade = CascadeType.MERGE,mappedBy = "userRole")
//    @JsonManagedReference
    @NotNull
    private List<User> user;

    @ManyToMany(cascade = CascadeType.MERGE)
    @NotNull
    private List<Roles> roleId;

    public long obtainUserId() {

        if(!user.isEmpty()){
            return user.get(0).getId();
        }
        return -1;
    }

    public List<Roles> obtainListOfRolesIds() {
        return roleId;
    }

    public long obtainRolesId(){return roleId.get(0).getRoleId();}

}
