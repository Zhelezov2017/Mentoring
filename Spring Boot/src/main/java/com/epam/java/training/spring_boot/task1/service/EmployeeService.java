package com.epam.java.training.spring_boot.task1.service;

import com.epam.java.training.spring_boot.task1.model.Employee;
import com.epam.java.training.spring_boot.task1.model.Position;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Set;

@Slf4j
@Service
public class EmployeeService {

    @Autowired
    @Qualifier("EMPLOYEES")
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

    @PostConstruct
    public void init() {
        log.info("Class Employee Service create!");
    }

    @PreDestroy
    public void close() {
        log.info("Class Employee Service destroy!");
    }

    void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }
}
