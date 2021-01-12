package com.edu.mano.covidregistration.domain;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
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
    @JoinColumn(name = "id")
    @Getter
    private User user;

    @OneToOne (cascade = CascadeType.MERGE)
    @JoinColumn(name = "roleId")
    private Roles roleId;

    public long obtainUserId() {
        return user.getId();
    }

    public UserRoles(){

    }
}
