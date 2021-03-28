package com.edu.mano.covidregistration.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Entity(name = "task_instances")
public class TaskInstance {
    @Id
    @ToString.Exclude
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_instance_id")
    private long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(
            name = "task_id",
            foreignKey = @ForeignKey(name = "fk_task_id"),
            nullable = false
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties("attributes")
    private Task task;

    @NotNull
    @Column(name = "request_id")
    private long request;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date createdTime;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date finishedTime;

    @NotNull
    @Column(columnDefinition = "boolean default true")
    boolean isActive;
}
