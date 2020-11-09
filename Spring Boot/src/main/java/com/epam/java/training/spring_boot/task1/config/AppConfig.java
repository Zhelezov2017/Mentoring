package com.epam.java.training.spring_boot.task1.config;

import com.epam.java.training.spring_boot.task1.model.Employee;
import com.epam.java.training.spring_boot.task1.model.Position;
import com.epam.java.training.spring_boot.task1.model.Salary;
import com.epam.java.training.spring_boot.task1.model.Skill;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.*;

@Configuration
@Import({FactoryBeanAppConfig.class})
public class AppConfig {

    @Bean
    @Qualifier("VLAD")
    public Employee vlad(Position junior) {
        return new Employee(1, "random", 12, "Koon", junior);
    }


    @Bean
    public Position junior(Map<Integer, Skill> skillMap) {
        return new Position("junior", new Salary(1000), Collections.singletonList(skillMap.get(1)));
    }

    @Bean
    public Position engineer(Map<Integer, Skill> skillMap) {
        return new Position("engineer", new Salary(3000), Arrays.asList(skillMap.get(2), skillMap.get(1)));
    }

    @Bean
    public Position director(Map<Integer, Skill> skillMap) {
        return new Position("director", new Salary(5000), Arrays.asList(skillMap.get(2), skillMap.get(1), skillMap.get(3)));
    }

    @Bean(name = "positionMap")
    public Map<Integer, Position> positionMap(Position junior, Position engineer, Position director) {
        Map<Integer, Position> myMap = new HashMap<>();
        myMap.put(1, junior);
        myMap.put(2, engineer);
        myMap.put(3, director);
        return myMap;
    }

    @Bean
    public Map<Integer, Skill> skillMap() {
        Map<Integer, Skill> skillMap = new HashMap<>();
        skillMap.put(1, new Skill("Java", 4));
        skillMap.put(2, new Skill("Python", 3));
        skillMap.put(3, new Skill("C++", 2));
        return skillMap;
    }

    @Bean
    @Qualifier(value = "EMPLOYEES")
    public Set<Employee> employees() {
        return new HashSet<>();
    }
}
