package com.edu.mano.covidregistration.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "User_request")
public class UserRequest {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @NotNull
    @Size(min = 3)
    private String treatmentState;


    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToMany (fetch = FetchType.LAZY)
    @JoinTable (name = "Request_symptoms"
            ,joinColumns = @JoinColumn(name = "request_id")
            ,inverseJoinColumns = @JoinColumn (name="symptom_id")
    )
    private List<Symptom> symptoms;

}
