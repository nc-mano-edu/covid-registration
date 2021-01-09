package com.edu.mano.covidregistration.domain;

import javax.persistence.*;

@Entity
public class UserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userRoleId;

    @OneToOne
    private User userId;

    @OneToMany
    private Roles roleId;

    public long getUserId() {
        return userId.getId();
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
