package com.edu.mano.covidregistration.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
//    @JsonBackReference
//    @Getter(AccessLevel.NONE)
    private UserRoles userRole;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;
    private String insuranceNumber;
    private String phoneNumber;

    public long getId() {
        return id;
    }

}