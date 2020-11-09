package com.epam.java.training.spring_boot.task1.model;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Salary {

    @NotNull
    private int salaryInDollars;

    public Salary(int salaryInDollars) {
        this.salaryInDollars = salaryInDollars;
    }

    public Integer getSalaryInDollars() {
        return salaryInDollars;
    }

    public void setSalaryInDollars(int salaryInDollars) {
        this.salaryInDollars = salaryInDollars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salary salary = (Salary) o;
        return Objects.equals(salaryInDollars, salary.salaryInDollars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salaryInDollars);
    }

    @Override
    public String toString() {
        return "Salary{" +
                "salaryInDollars=" + salaryInDollars +
                '}';
    }
}
