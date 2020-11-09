package com.epam.java.training.jvm.task2.model;

import com.epam.java.training.jvm.task4.interfaceRealize.Animal;

import java.util.Objects;

/**
 * Created by Vladislav Zhelezov.
 */
public class Cat implements Animal {

    private String name;
    private Integer age;

    public Cat(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public void play() {
        System.out.println("I play with cat!");
    }

    @Override
    public void voice() {
        System.out.println("Meow!");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return Objects.equals(name, cat.name) &&
                Objects.equals(age, cat.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
