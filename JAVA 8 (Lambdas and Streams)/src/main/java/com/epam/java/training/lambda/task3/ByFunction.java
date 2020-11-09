package com.epam.java.training.lambda.task3;

@FunctionalInterface
public interface ByFunction<U> {

    String grow(U u);

    default String study() {
        //Minimal logic
        System.out.println("Learn!");
        return "People studies";
    }

    default void teacherAssessment(String name) {
        System.out.println(name);
    }

    static Integer assessment(String s) {
        return Integer.valueOf(s);
    }

    static void learn() {
        System.out.println("Study as at school");
    }
}
