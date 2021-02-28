package com.edu.mano.covidregistration.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "attribute_types")
public class AttributeType {
    @Id
    @ToString.Exclude
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attribute_type_id")
    private long id;

    @NotNull
    @Size(min = 2)
    private String name;

    private String checkMask;
}
