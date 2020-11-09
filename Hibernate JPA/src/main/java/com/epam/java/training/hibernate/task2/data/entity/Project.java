package com.epam.java.training.hibernate.task2.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@Table(name = "PROJECTS")
public class Project {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "NAME")
    private String name;
    @ManyToMany(mappedBy = "projects")
    private Set<Employee> employees = new HashSet<>();
}
