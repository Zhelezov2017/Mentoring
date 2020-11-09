package com.epam.java.training.lambda.task6;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CustomCollector customCollector = new CustomCollector();
        List<Integer> count = initStudent().stream().collect(customCollector);
        System.out.println(count);
    }

    private static List<Student> initStudent() {
        List<Integer> arrayListVasya = Arrays.asList(12, 34, 35);
        List<Integer> arrayListAndrey = Arrays.asList(42, 38, 43);
        List<Integer> arrayListAlex = Arrays.asList(18, 1, 98);
        return Arrays.asList(new Student("Vasya", 23, arrayListVasya, "Russian"),
                new Student("Andrey", 27, arrayListAndrey, "Russian"),
                new Student("Alex", 45, arrayListAlex, "English"));
    }
}
