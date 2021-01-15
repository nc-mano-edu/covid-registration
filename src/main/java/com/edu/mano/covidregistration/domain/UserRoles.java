package com.edu.mano.covidregistration.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "USER_ROLES")
public class UserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_role_id")
    private long userRoleId;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @ManyToMany(cascade = CascadeType.MERGE)
    @NotNull
    private List<Roles> roleId;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRoleId(List<Roles> roleId) {
        this.roleId = roleId;
    }

    public long getUserRoleId() {
        return userRoleId;
    }

    public long obtainUserId() {
        return user.getId();
    }

    public List<Roles> getRoleId() {
        return roleId;
    }

}
