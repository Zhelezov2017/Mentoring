package com.epam.java.training.hibernate.task2.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@Table(name = "UNITS")
public class Unit {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "EMAIL")
    private String email;
    @OneToMany(mappedBy="unit")
    private Set<Employee> employees = new HashSet<>();
}
