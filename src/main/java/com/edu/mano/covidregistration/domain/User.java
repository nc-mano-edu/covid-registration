package com.edu.mano.covidregistration.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotNull
    @Size(min = 2)
    private String firstName;
    @NotNull
    @Size(min = 2)
    private String middleName;
    @NotNull
    @Size(min = 2)
    private String lastName;

    @OneToMany(mappedBy = "user")
    @Getter(AccessLevel.NONE)
    private List<UserRoles> userRole;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="dd.mm.yyyy")
    private Date dateOfBirth;
    private String insuranceNumber;
    private String phoneNumber;

    public long getId() {
        return id;
    }

}