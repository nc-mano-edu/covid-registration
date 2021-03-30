package com.edu.mano.covidregistration.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user_request")
public class UserRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Size(min = 3)
    private String treatmentState;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private User user;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            joinColumns = @JoinColumn(
                    name = "request_id",
                    foreignKey = @ForeignKey(
                            name = "fk_request_id",
                            foreignKeyDefinition = "FOREIGN KEY (request_id) REFERENCES user_request(request_id) ON DELETE CASCADE")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "symptom_id",
                    foreignKey = @ForeignKey(
                            name = "fk_symptom_id",
                            foreignKeyDefinition = "FOREIGN KEY (symptom_id) REFERENCES symptoms(symptom_id) ON DELETE CASCADE")
            )
    )
    private List<Symptom> symptoms;

}
