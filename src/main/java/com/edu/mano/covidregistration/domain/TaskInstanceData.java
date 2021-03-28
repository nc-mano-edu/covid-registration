package com.edu.mano.covidregistration.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
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
    private long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(
            name = "task_instance_id",
            foreignKey = @ForeignKey(name = "fk_task_instance_id"),
            nullable = false
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
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
