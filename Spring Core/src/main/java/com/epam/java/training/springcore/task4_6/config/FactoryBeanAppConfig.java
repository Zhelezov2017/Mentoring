package com.epam.java.training.springcore.task4_6.config;

import com.epam.java.training.springcore.task4_6.factory.EmployeeFactory;
import com.epam.java.training.springcore.task4_6.model.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


@Configuration
public class FactoryBeanAppConfig {

    @Bean(name = "Employee")
    public EmployeeFactory employeeFactory() {
        int factoryId = 1;
        String factoryName = "Good Factory";
        EmployeeFactory factory = new EmployeeFactory(factoryId, factoryName);
        factory.setEmployeeId(2);
        return factory;
    }

    @Bean
    @Scope(value = "prototype")
    public Employee employee(EmployeeFactory employeeFactory) throws Exception {
        return employeeFactory.getObject();
    }
}
