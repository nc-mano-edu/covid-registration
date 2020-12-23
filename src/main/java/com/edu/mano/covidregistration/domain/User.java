package com.edu.mano.covidregistration.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class User {
    
    private long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date dateOfBirth;
    private String insuranceNumber;
    private String phoneNumber;

}