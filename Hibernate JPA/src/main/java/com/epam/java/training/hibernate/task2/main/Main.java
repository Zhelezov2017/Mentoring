package com.epam.java.training.hibernate.task2.main;

import com.epam.java.training.hibernate.task2.data.entity.Employee;
import com.epam.java.training.hibernate.task2.data.entity.Project;
import com.epam.java.training.hibernate.task2.data.entity.Unit;
import com.epam.java.training.hibernate.task2.service.impl.EmployeeServiceImpl;
import com.epam.java.training.hibernate.task2.service.impl.ProjectServiceImpl;
import com.epam.java.training.hibernate.task2.service.impl.UnitServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {


    public static void main(String[] args) {
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        ProjectServiceImpl projectService = new ProjectServiceImpl();
        UnitServiceImpl unitService = new UnitServiceImpl();

        Employee employee = new Employee();

        employee.setId(1L);
        employee.setExternal(false);
        employeeService.create(employee);

        Project project = new Project();
        project.setId(1L);
        project.setName("Alias");
        projectService.create(project);

        Unit unit = new Unit();
        unit.setId(1L);
        unit.setName("Order");
        unitService.create(unit);

        employeeService.assignEmployeeForProject(1L, 1L);
        employeeService.addEmployeeToUnit(1L, 1L);

        log.info("Are not external projects:" + employeeService.getProjectsWithEmployeesWhichAreNotExternal());

        employeeService.close();
    }
}
