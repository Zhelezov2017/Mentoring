package com.epam.java.training.webservices.task1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String email;
    @JsonIgnore
    private byte[] imageFile;
}
