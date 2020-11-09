package com.epam.java.training.jvm.task1.model;

import java.util.Objects;

/**
 * Created by Vladislav Zhelezov.
 */
public class Person {

    private Car car;

    public Person() {
        car = new Car();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(car, person.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(car);
    }

    @Override
    public String toString() {
        return "Person{" +
                "car=" + car +
                '}';
    }
}
