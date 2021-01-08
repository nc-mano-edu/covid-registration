package com.edu.mano.covidregistration.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userRoleId;

    private User userId;

    private Roles roleId;

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Roles getRoleId() {
        return roleId;
    }

    public void setRoleId(Roles roleId) {
        this.roleId = roleId;
    }

}
