package com.epam.java.training.springcore.task4_6;

import com.epam.java.training.springcore.task4_6.model.Employee;
import com.epam.java.training.springcore.task4_6.model.Position;
import com.epam.java.training.springcore.task4_6.model.Salary;
import com.epam.java.training.springcore.task4_6.model.Skill;
import com.epam.java.training.springcore.task4_6.service.EmployeeService;
import com.epam.java.training.springcore.task4_6.service.PositionService;
import com.epam.java.training.springcore.task4_6.service.SalaryService;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;


public class Main {

    private final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        AbstractApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.epam.java.training.springcore.task4_6");

        //1 year
        //Get services
        EmployeeService employeeService = (EmployeeService) applicationContext.getBean("employeeService");
        PositionService positionService = (PositionService) applicationContext.getBean("positionService");
        SalaryService salaryService = (SalaryService) applicationContext.getBean("salaryService");

        Employee myEmployee = (Employee) applicationContext.getBean("vlad");
        logger.info("Test my annotation InjectRandomString: " + myEmployee);

        //Beans with Entity system
        Map<Integer, Position> positionMap = (Map<Integer, Position>) applicationContext.getBean("positionMap");
        Map<Integer, Skill> skillMap = (Map<Integer, Skill>) applicationContext.getBean("skillMap");
        Set<Employee> employees = (Set<Employee>) applicationContext.getBean("employees");

        positionMap.forEach((e, p) -> logger.info("Before inflation: " + p));
        //Across of fabric method building employee
        Employee alexBean = (Employee) applicationContext.getBean("employee");
        Employee vladBean = (Employee) applicationContext.getBean("employee");
        Employee andreyBean = (Employee) applicationContext.getBean("employee");

        //Setting Objects
        Employee alex = settingEmployee(alexBean, "Alex", 25, "Rook");
        Employee vlad = settingEmployee(vladBean, "Vlad", 26, "Kapella");
        Employee andrey = settingEmployee(andreyBean, "Andrey", 34, "Cool");

        //One years
        logger.info("Start inflation on 1 years");
        salaryService.inflationOccurredForThisPosition();
        positionMap.forEach((e, p) -> logger.info("After inflation: " + p));

        //Hire Employee
        employeeService.hire(alex, positionMap.get(3));
        employeeService.hire(vlad, positionMap.get(2));
        employeeService.hire(andrey, positionMap.get(1));
        logger.info("New employee: " + employees.toString());

        logger.info("Add Project Manager");
        positionService.create(4, new Position("Project Manager", new Salary(10000), Arrays.asList(skillMap.get(1), skillMap.get(2), skillMap.get(3))));

        logger.info("Delete position junior");
        positionService.delete(1);
        positionMap.forEach((e, p) -> logger.info("Remained position: " + p));

        logger.info("Update position engineer");
        positionService.update(2, new Position("engineer", new Salary(4500), Arrays.asList(skillMap.get(1), skillMap.get(2))));
        positionMap.forEach((e, p) -> logger.info("Position: " + p));

        logger.info("Return director: " + positionService.read(3));

        //Two years
        logger.info("Start inflation on 2 years");
        salaryService.inflationOccurredForThisPosition();
        salaryService.courseDollar(6);
        positionMap.forEach((e, p) -> logger.info("After inflation and fall course dollar: " + p));

        logger.info("Add Junior Manager position");
        positionService.create(5, new Position("Junior Manager", new Salary(2300), Collections.singletonList(skillMap.get(1))));

        logger.info("Update position director");
        positionService.update(3, new Position("director", new Salary(7500), Arrays.asList(skillMap.get(1), skillMap.get(2), skillMap.get(3))));
        positionMap.forEach((e, p) -> logger.info("Position in Map: " + p));

        logger.info("Delete employee Vlad");
        employeeService.fire(vlad);
        logger.info("Who stayed : " + employees.toString());
        //It's all


        //Spel Spring
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("name");
        EvaluationContext context = new StandardEvaluationContext(vlad);
        String name = (String) exp.getValue(context);
        logger.info("Name Vlad: " + name);

        //destroy method
        applicationContext.close();
    }

    private static Employee settingEmployee(Employee employee, String name, Integer age, String office) {
        employee.setName(name);
        employee.setAge(age);
        employee.setOffice(office);
        return employee;
    }
}
