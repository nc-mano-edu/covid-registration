package com.edu.mano.covidregistration.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ROLES")
public class Roles {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private long roleId;

    private String roleName;

    private String description;

    @OneToOne
    @Getter(AccessLevel.NONE)
    private UserRoles userRoleId;

    public long getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Roles(){

    }
}
