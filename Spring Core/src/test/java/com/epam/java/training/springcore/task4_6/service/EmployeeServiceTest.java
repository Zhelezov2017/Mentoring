package com.epam.java.training.springcore.task4_6.service;

import com.epam.java.training.springcore.task4_6.model.Employee;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Vladislav Zhelezov.
 */

public class EmployeeServiceTest {


    private Set<Employee> employees = new HashSet<>();

    private EmployeeService employeeService = new EmployeeService();


    @Test
    public void hire() {
        employeeService.setEmployees(employees);
        employeeService.hire(new Employee(1, "Vlad", 13, "Grom",
                null), null);
    }

    @Test
    public void fire() {
        employeeService.setEmployees(employees);
        employeeService.fire(new Employee(3, "Vlad", 8, "Grom", null));
    }
}