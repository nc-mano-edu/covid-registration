package com.edu.mano.covidregistration.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tasks")
public class Task {
    @Id
    @ToString.Exclude
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;

    @NotNull
    @Size(min = 2)
    private String name;

    @NotNull
    @Pattern(
            regexp = "(((\\d+,)+\\d+|(\\d+(\\\\/|-)\\d+)|\\d+|\\*) ?){5,7}",
            message = "должно соответствовать cron-формату"
    )
    private String schedule;

    @Size(min = 10)
    private String description;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "task_attributes",
            joinColumns = @JoinColumn(
                    name = "task_id",
                    foreignKey = @ForeignKey(
                            name = "fk_task_id",
                            foreignKeyDefinition = "FOREIGN KEY (task_id) REFERENCES tasks(task_id) ON DELETE CASCADE")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "attribute_id",
                    foreignKey = @ForeignKey(
                            name = "fk_attribute_id",
                            foreignKeyDefinition = "FOREIGN KEY (attribute_id) REFERENCES attributes(attribute_id) ON DELETE CASCADE")
            )
    )
    private List<Attribute> attributes = new ArrayList<>();
}
