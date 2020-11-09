package com.epam.java.training.nosql.task2.entity;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity("TASKS")
public class Task {
    @Id
    private Long id;
    private LocalDate dateOfCreation;
    private LocalDate deadline;
    private String name;
    private String description;
    @Reference
    private List<SubTask> subTasks = new ArrayList<>();
    private String category;

    public Task(Long id, LocalDate dateOfCreation, LocalDate deadline, String name, String description, String category) {
        this.id = id;
        this.dateOfCreation = dateOfCreation;
        this.deadline = deadline;
        this.name = name;
        this.description = description;
        this.category = category;
    }
}

