package com.epam.java.training.jdbc.task1_5.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "USERS")
public class User {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SURNAME")
    private String surname;
    @Column(name = "BIRTH_DATE")
    private Date birthDate;
}
