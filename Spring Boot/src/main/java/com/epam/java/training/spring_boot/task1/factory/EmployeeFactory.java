package com.epam.java.training.spring_boot.task1.factory;

import com.epam.java.training.spring_boot.task1.model.Employee;
import com.epam.java.training.spring_boot.task1.model.Position;
import org.springframework.beans.factory.config.AbstractFactoryBean;

/**
 * Created by Vladislav Zhelezov.
 */
public class EmployeeFactory extends AbstractFactoryBean<Employee> {

    private int factoryId;
    private String factoryName;
    private int EmployeeId;
    private String name;
    private int age;
    private String office;
    private Position position;

    @Override
    public Class<?> getObjectType() {
        return Employee.class;
    }

    @Override
    protected Employee createInstance() {
        return new Employee(EmployeeId, name, age, office, position);
    }

    public EmployeeFactory(int factoryId, String factoryName) {
        setSingleton(false);
        this.factoryId = factoryId;
        this.factoryName = factoryName;
    }

    public int getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(int factoryId) {
        this.factoryId = factoryId;
    }

    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int employeeId) {
        EmployeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }
}
