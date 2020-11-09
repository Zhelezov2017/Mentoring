package com.epam.java.training.hibernate.task2.service.impl;

import com.epam.java.training.hibernate.task2.data.entity.Employee;
import com.epam.java.training.hibernate.task2.data.entity.Employee_;
import com.epam.java.training.hibernate.task2.data.entity.Project;
import com.epam.java.training.hibernate.task2.data.entity.Unit;
import com.epam.java.training.hibernate.task2.service.EmployeeService;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Set;

import static com.epam.java.training.hibernate.task2.configuration.EntityManagerConfiguration.entityManager;
import static com.epam.java.training.hibernate.task2.configuration.EntityManagerConfiguration.entityManagerFactory;

public class EmployeeServiceImpl implements EmployeeService {

    private UnitServiceImpl unitService = new UnitServiceImpl();
    private ProjectServiceImpl projectService = new ProjectServiceImpl();


    @Override
    public Employee addEmployeeToUnit(Long employeeId, Long unitId) {
        Unit unit = unitService.findById(unitId);
        Set<Employee> employees = unit.getEmployees();

        entityManager.getTransaction().begin();
        Employee employee = entityManager.find(Employee.class, employeeId);
        employees.add(employee);
        entityManager.getTransaction().commit();
        return employee;
    }

    @Override
    public Employee assignEmployeeForProject(Long employeeId, Long projectId) {
        Project project = projectService.findById(projectId);
        entityManager.getTransaction().begin();
        Set<Employee> employees = project.getEmployees();
        Employee employee = entityManager.find(Employee.class, employeeId);
        employees.add(employee);
        entityManager.getTransaction().commit();
        return employee;
    }

    @Override
    public List<Project> getProjectsWithEmployeesWhichAreNotExternal() {
        entityManager.getTransaction().begin();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Project> cq = cb.createQuery(Project.class);
        Root<Employee> root= cq.from(Employee.class);
        Join<Object, Object> join = root.join(Employee_.PROJECTS);
        Expression<Boolean> external = join.get(Employee_.EXTERNAL);
        Predicate pred = cb.isFalse(external);
        cq.where(pred);
        TypedQuery<Project> query = entityManager.createQuery(cq);
        entityManager.getTransaction().commit();
        return query.getResultList();
    }

    @Override
    public Employee create(Employee employee) {
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
        return employee;
    }

    @Override
    public Employee findById(Long id) {
        entityManager.getTransaction().begin();
        Employee employee = entityManager.find(Employee.class, id);
        entityManager.getTransaction().commit();
        return employee;
    }

    @Override
    public Employee delete(Long id) {
        entityManager.getTransaction().begin();
        Employee employee = entityManager.find(Employee.class, id);
        entityManager.remove(employee);
        entityManager.getTransaction().commit();
        return employee;
    }

    @Override
    public Employee update(Long id) {
        entityManager.getTransaction().begin();
        Employee employee = entityManager.find(Employee.class, id);
        Employee mergeEmployee = entityManager.merge(employee);
        entityManager.getTransaction().commit();
        return mergeEmployee;
    }

    @Override
    public void close() {
        entityManagerFactory.close();
    }
}
