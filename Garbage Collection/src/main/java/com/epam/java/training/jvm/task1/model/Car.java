package com.epam.java.training.jvm.task1.model;

import java.util.Objects;

/**
 * Created by Vladislav Zhelezov.
 */
public class Car {

    private String mark;
    private Person master;

    public Car() {
        mark = "Mers";
        master = new Person();
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public void setMaster(Person master) {
        this.master = master;
    }

    public Person getMaster() {
        return master;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(mark, car.mark) &&
                Objects.equals(master, car.master);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mark, master);
    }

    @Override
    public String toString() {
        return "Car{" +
                "mark='" + mark + '\'' +
                '}';
    }
}
