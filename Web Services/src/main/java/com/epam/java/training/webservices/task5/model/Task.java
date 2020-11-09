package com.epam.java.training.webservices.task5.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class Task extends RepresentationModel<Task> {
    private Long id;
    private String name;
    private String description;
    private LocalDate creationDate;
    private LocalDate deadLine;
}
