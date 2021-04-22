package com.edu.mano.covidregistration.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.sql.Blob;
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

    @Lob
    private Blob imageValue;

    private Date dateValue;

    private Integer numericValue;
}
