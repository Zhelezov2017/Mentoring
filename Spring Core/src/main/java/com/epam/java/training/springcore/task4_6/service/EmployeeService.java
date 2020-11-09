package com.epam.java.training.springcore.task4_6.service;

import com.epam.java.training.springcore.task4_6.model.Employee;
import com.epam.java.training.springcore.task4_6.model.Position;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Set;

/**
 * Created by Vladislav Zhelezov.
 */


public class EmployeeService {

    private final static Logger logger = Logger.getLogger(EmployeeService.class);

    @Autowired
    @Qualifier
    private Set<Employee> employees;

    public void hire(Employee employee, Position position) {
        //Position don't should to be null
        if (position == null) throw new NullPointerException();
        employee.setPosition(position);
        employees.add(employee);
    }


    public void fire(Employee employee) {
        //Employee don't should to be null(I don't know that throw because throw new ArrayIndexOutOfBoundsException())
        boolean hasEmp = employees.stream()
                .anyMatch(e -> e.equals(employee));
        if (hasEmp){
            employees.remove(employee);
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void init() {
        logger.info("Class Employee Service create!");
    }

    public void close() {
        logger.info("Class Employee Service destroy!");
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }
}
