package com.edu.mano.covidregistration.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "covid_users")
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

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;
    private String insuranceNumber;
    private String phoneNumber;

    @NotNull
    @Size(min = 2)
    private String email;

    @NotNull
    @Size(min = 2)
    private String password;

    @NotNull
    @Size(min = 1)
    private String username;

    @ManyToMany
    private List<Specialisation> specialisations;

    @ManyToMany/*(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)*/
    private List<Role> roles = new ArrayList<>();
}