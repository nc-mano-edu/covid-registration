package com.edu.mano.covidregistration.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Data
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

    public long getRoleId() {
        return roleId;
    }

}
