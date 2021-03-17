package com.edu.mano.covidregistration.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Specialisation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @Size(min = 2)
    @Column(unique = true)
    private String name;

    private String description;

}
