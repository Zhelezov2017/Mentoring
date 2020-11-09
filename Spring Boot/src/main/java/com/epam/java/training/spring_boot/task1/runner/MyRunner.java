package com.epam.java.training.spring_boot.task1.runner;

import com.epam.java.training.spring_boot.task1.model.Employee;
import com.epam.java.training.spring_boot.task1.model.Position;
import com.epam.java.training.spring_boot.task1.model.Salary;
import com.epam.java.training.spring_boot.task1.model.Skill;
import com.epam.java.training.spring_boot.task1.service.EmployeeService;
import com.epam.java.training.spring_boot.task1.service.PositionService;
import com.epam.java.training.spring_boot.task1.service.SalaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private SalaryService salaryService;
    @Autowired
    @Qualifier("EMPLOYEE")
    private Employee employeeAndrey;
    @Autowired
    @Qualifier("EMPLOYEE")
    private Employee employeeVlad;
    @Autowired
    @Qualifier("EMPLOYEE")
    private Employee employeeAlex;
    @Autowired
    private Map<Integer, Position> positionMap;
    @Autowired
    private Map<Integer, Skill> skillMap;
    @Autowired
    @Qualifier("EMPLOYEES")
    private Set<Employee> employees;
    @Autowired
    @Qualifier("VLAD")
    private Employee vlad;


    @Override
    public void run(String... args) throws Exception {
        Employee myEmployee = vlad;
        log.debug("Test my annotation InjectRandomString: " + myEmployee);

        positionMap.forEach((e, p) -> log.debug("Before inflation: " + p));
        //Across of fabric method building employee
        Employee alexBean = employeeAlex;
        Employee vladBean = employeeVlad;
        Employee andreyBean = employeeAndrey;

        //Setting Objects
        Employee alex = settingEmployee(alexBean, "Alex", 25, "Rook");
        Employee vlad = settingEmployee(vladBean, "Vlad", 26, "Kapella");
        Employee andrey = settingEmployee(andreyBean, "Andrey", 34, "Cool");

        //One years
        log.debug("Start inflation on 1 years");
        salaryService.inflationOccurredForThisPosition();
        positionMap.forEach((e, p) -> log.debug("After inflation: " + p));

        //Hire Employee
        employeeService.hire(alex, positionMap.get(3));
        employeeService.hire(vlad, positionMap.get(2));
        employeeService.hire(andrey, positionMap.get(1));
        log.debug("New employee: " + employees.toString());

        log.debug("Add Project Manager");
        positionService.create(4, new Position("Project Manager", new Salary(10000), Arrays.asList(skillMap.get(1), skillMap.get(2), skillMap.get(3))));

        log.debug("Delete position junior");
        positionService.delete(1);
        positionMap.forEach((e, p) -> log.debug("Remained position: " + p));

        log.debug("Update position engineer");
        positionService.update(2, new Position("engineer", new Salary(4500), Arrays.asList(skillMap.get(1), skillMap.get(2))));
        positionMap.forEach((e, p) -> log.debug("Position: " + p));

        log.debug("Return director: " + positionService.read(3));

        //Two years
        log.debug("Start inflation on 2 years");
        salaryService.inflationOccurredForThisPosition();
        salaryService.courseDollar(6);
        positionMap.forEach((e, p) -> log.debug("After inflation and fall course dollar: " + p));

        log.debug("Add Junior Manager position");
        positionService.create(5, new Position("Junior Manager", new Salary(2300), Collections.singletonList(skillMap.get(1))));

        log.debug("Update position director");
        positionService.update(3, new Position("director", new Salary(7500), Arrays.asList(skillMap.get(1), skillMap.get(2), skillMap.get(3))));
        positionMap.forEach((e, p) -> log.debug("Position in Map: " + p));

        log.debug("Delete employee Vlad");
        employeeService.fire(vlad);
        log.debug("Who stayed : " + employees.toString());
        //It's all


        //Spell Spring
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("name");
        EvaluationContext context = new StandardEvaluationContext(vlad);
        String name = (String) exp.getValue(context);
        log.debug("Name Vlad: " + name);

    }

    private static Employee settingEmployee(Employee employee, String name, Integer age, String office) {
        employee.setName(name);
        employee.setAge(age);
        employee.setOffice(office);
        return employee;
    }
}
