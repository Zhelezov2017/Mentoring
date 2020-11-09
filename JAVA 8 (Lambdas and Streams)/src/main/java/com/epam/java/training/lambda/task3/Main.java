package com.epam.java.training.lambda.task3;

import com.epam.java.training.lambda.task2.model.Person;

import java.util.Scanner;
import java.util.function.*;

public class Main {

    public static void main(String[] args) {
        Predicate<Integer> isPositive = x -> x > 0;
        BinaryOperator<Integer> multiply = (x, y) -> x * y * 3 / 2;
        UnaryOperator<Integer> square = x -> x * x * 2 + 36;
        Function<Integer, String> convert = x -> x + " Age";
        Consumer<Integer> printer = String::valueOf;
        Supplier<Person> userFactory = () -> {
            Scanner in = new Scanner(System.in);
            System.out.println("Name : ");
            String name = in.nextLine();
            System.out.println("Age : ");
            Integer age = in.nextInt();
            return new Person(name, age);
        };
        //Anonymous class
        new ByFunction<Integer>() {
            @Override
            public String grow(Integer o) {
                return "Hello" + o;
            }

            @Override
            public String study() {
                return "We love you";
            }
        };
        //Execute functional interface
        ByFunction<String> byFunction = s -> "I love you";
        ByFunction<String> byFunctionInner = r -> byFunction.study();
        ByFunction<String> byFunctionInnerTrue = r -> byFunctionInner.grow("Hi!");
        byFunctionInnerTrue.teacherAssessment("Hi!");
        byFunction.study();
        ByFunction.learn();

    }
}
