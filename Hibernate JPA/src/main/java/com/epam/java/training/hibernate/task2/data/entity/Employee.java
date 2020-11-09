package com.epam.java.training.hibernate.task2.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@Table(name = "EMPLOYEES")
public class Employee {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;
    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)
    private EmployeeStatus employeeStatus;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "EMPLOYEE_PROJECT",
            joinColumns = {@JoinColumn(name = "EMPLOYEE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "PROJECT_ID")})
    Set<Project> projects = new HashSet<>();
    @OneToOne(mappedBy = "employee")
    private EmployeePersonalInfo employeePersonalInfo;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UNIT_ID")
    private Unit unit;
    @Column(name = "EXTERNAL")
    private Boolean external;
}
