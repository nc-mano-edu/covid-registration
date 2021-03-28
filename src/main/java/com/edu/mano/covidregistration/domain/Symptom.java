package com.edu.mano.covidregistration.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Symptoms")
public class Symptom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long symptomId;

    @Size(min = 2, max = 200)
    private String name;
    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "symptoms"/*, fetch = FetchType.EAGER*/)
    List<UserRequest> userRequests;
}
