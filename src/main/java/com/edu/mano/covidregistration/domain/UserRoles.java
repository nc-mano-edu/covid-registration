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
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany (cascade = CascadeType.MERGE)
    private List<Roles> roleId;

    public User getUser() {
        return user;
    }

    public long obtainUserId() {
        return user.getId();
    }

    public UserRoles(){

    }
}
