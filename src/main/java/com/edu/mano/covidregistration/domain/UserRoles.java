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

    @ManyToOne()
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @ManyToMany(cascade = CascadeType.MERGE)
    @NotNull
    private List<Roles> roleId;

    public long obtainUserId() {

        return user.getId();
    }

    public List<Roles> obtainListOfRolesIds() {
        return roleId;
    }

    public long obtainRolesId(){return roleId.get(0).getRoleId();}

}
