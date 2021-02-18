package com.edu.mano.covidregistration.domain;

import com.edu.mano.covidregistration.domain.UserRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

     @NotNull
    @Size(min = 2, max = 200)
    private String name;
    private String description;

    /*
    public Symptom (Long symptomId , String name, String description) {
        this.symptomId = symptomId ;
        this.name = name;
        this.description = description;
    }*/

    @ManyToMany (fetch = FetchType.LAZY)
    @JoinTable (name = "Request_symptoms"
            ,joinColumns = @JoinColumn(name = "symptom_id")
            ,inverseJoinColumns = @JoinColumn (name="request_id")
    )
    private List<UserRequest> userRequests;

}
