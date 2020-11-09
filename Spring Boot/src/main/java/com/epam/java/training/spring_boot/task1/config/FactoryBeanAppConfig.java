package com.epam.java.training.spring_boot.task1.config;

import com.epam.java.training.spring_boot.task1.factory.EmployeeFactory;
import com.epam.java.training.spring_boot.task1.model.Employee;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier(value = "EMPLOYEE")
    @Scope("prototype")
    public Employee employee(EmployeeFactory employeeFactory) throws Exception {
        return employeeFactory.getObject();
    }
}
