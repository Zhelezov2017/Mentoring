package com.epam.java.training.webservices.task5.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class User extends RepresentationModel<User> {
    private Long id;
    private String name;
    private String surname;
    private String mail;
    private List<Task> tasks = new ArrayList<>();
}
