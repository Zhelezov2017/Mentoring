package com.epam.java.training.springcore.task4_6.model;

import java.util.List;
import java.util.Objects;

/**
 * Created by Vladislav Zhelezov.
 */

public class Position {

    private String name;

    private Salary salary;

    private List<Skill> skills;

    public Position(String name, Salary salary, List<Skill> skills) {
        this.name = name;
        this.salary = salary;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(name, position.name) &&
                Objects.equals(salary, position.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, salary);
    }

    @Override
    public String toString() {
        return "Position{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
