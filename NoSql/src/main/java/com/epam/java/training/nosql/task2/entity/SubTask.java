package com.epam.java.training.nosql.task2.entity;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import lombok.*;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity("SUB_TASKS")
public class SubTask {
    @Id
    private Long id;
    private String name;
    private String description;
}
