package com.epam.java.training.hibernate.task2.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "EMPLOYEE_PERSONAL_INFO")
public class EmployeePersonalInfo {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "FIRST_NAME", nullable = false, length = 100)
    private String firstName;
    @Basic
    @Column(name = "LAST_NAME", nullable = false, length = 100)
    private String lastName;
    @Column(name = "PHONE", nullable = false, length = 50)
    private String phone;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "id")
    private Employee employee;
}
