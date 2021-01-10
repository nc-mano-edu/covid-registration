package com.edu.mano.covidregistration.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USER_ROLES")
public class UserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_role_id")
    private long userRoleId;

//    @OneToOne
//    private User userId;

    @OneToMany (mappedBy = "userRoleId")
    @Column(name = "role_id")
    private List<Roles> roleId;

//    public long getUserId() {
//        return userId.getId();
//    }
//
//    public void setUserId(User userId) {
//        this.userId = userId;
//    }

    public UserRoles(){

    }
}
