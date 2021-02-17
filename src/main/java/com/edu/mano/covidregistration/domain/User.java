package com.edu.mano.covidregistration.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;
    private String middleName;
    private String lastName;

    @OneToMany(mappedBy = "user")
//    @JsonBackReference
    @Getter(AccessLevel.NONE)
    private List<UserRoles> userRole;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;
    private String insuranceNumber;
    private String phoneNumber;

    public long getId() {
        return id;
    }

}