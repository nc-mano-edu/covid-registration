package com.edu.mano.covidregistration.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "task_instance_data")
public class TaskInstanceData {
    @Id
    @ToString.Exclude
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_instance_data_id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(
            name = "task_instance_id",
            foreignKey = @ForeignKey(name = "fk_task_instance_id"),
            nullable = false
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"task", "request", "data", "createdTime", "finishedTime"})
    private TaskInstance taskInstance;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(
            name = "attribute_id",
            foreignKey = @ForeignKey(name = "fk_attribute_id"),
            nullable = false
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Attribute attribute;

    private String stringValue;

    @Column(columnDefinition = "text")
    private String imageValue;

    private Date dateValue;

    private Integer numericValue;
}
