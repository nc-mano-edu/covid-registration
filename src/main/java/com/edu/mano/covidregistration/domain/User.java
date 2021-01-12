package com.edu.mano.covidregistration.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

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

    @OneToOne(mappedBy = "user", cascade = CascadeType.MERGE)
    @Getter(AccessLevel.NONE)
    private UserRoles userRole;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;
    private String insuranceNumber;
    private String phoneNumber;

    public long getId() {
        return id;
    }

}