package com.epam.java.training.hibernate.task2.service;

import com.epam.java.training.hibernate.task2.data.entity.Employee;
import com.epam.java.training.hibernate.task2.data.entity.Project;

import java.util.List;


public interface EmployeeService extends CrudService<Employee> {
    Employee addEmployeeToUnit(Long employeeId, Long unitId);

    Employee assignEmployeeForProject(Long employeeId, Long projectId);

    List<Project> getProjectsWithEmployeesWhichAreNotExternal();
}
