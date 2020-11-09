package com.epam.java.training.springcore.task4_6.model;

import com.epam.java.training.springcore.task4_6.annotation.InjectRandomString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Created by Vladislav Zhelezov.
 */

public class Employee {

    @NotNull
    private Integer id;

    @InjectRandomString(randomString = {"vlad", "anton", "artem", "andrey"})
    private String name;

    @Min(value = 18)
    private int age;

    @Size(min = 5, message = "length min 5")
    private String office;

    @NotNull
    private Position position;

    public Employee(Integer id, String name, int age, String office, Position position) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.office = office;
        this.position = position;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) &&
                Objects.equals(office, employee.office) &&
                Objects.equals(position, employee.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, office, position);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", office='" + office + '\'' +
                ", position=" + position +
                '}';
    }
}
