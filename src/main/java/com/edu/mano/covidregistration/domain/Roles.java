package com.edu.mano.covidregistration.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ROLES")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private long roleId;

    @Size(min = 2)
    private String roleName;

    private String description;

    @ManyToMany
    @Getter(AccessLevel.NONE)
    private List<UserRoles> userRoleId;

    public Roles(long id, String roleName, String description){

        this.roleId = id;

        this. roleName = roleName;

        this.description = description;
    }

    public long getRoleId() {
        return roleId;
    }

    public List<UserRoles> obtainListOfUserRoles(){

        return userRoleId;
    }
}
