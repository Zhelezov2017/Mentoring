package com.epam.java.training.lambda.task6;

import java.util.List;
import java.util.Objects;


public class Student {
    private String name;
    private Integer age;
    private List<Integer> languageLevel;
    private String languageNative;

    Student(String name, Integer age, List<Integer> languageLevel, String languageNative) {
        this.name = name;
        this.age = age;
        this.languageLevel = languageLevel;
        this.languageNative = languageNative;
    }


    protected Integer calculateLevel(List<Integer> languageLevel) {
        return languageLevel.stream().mapToInt(e -> e).sum();
    }

    Integer calculateLoverInNative(Integer age) {
        return age + languageLevel.get(0);
    }


    Integer getAge() {
        return age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, languageLevel, languageNative);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) &&
                Objects.equals(age, student.age) &&
                Objects.equals(languageLevel, student.languageLevel) &&
                Objects.equals(languageNative, student.languageNative);
    }

    @Override
    public String toString() {
        return "A{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", languageLevel=" + languageLevel +
                ", languageNative='" + languageNative + '\'' +
                '}';
    }

}
