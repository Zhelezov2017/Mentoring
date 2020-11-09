package com.epam.java.training.lambda.task4;

public class Main {

    public static void main(String[] args) {
        //2 Implementation
        ThirdFunction<Integer, Integer, Integer, String> countStrength = (Integer strength, Integer agility, Integer mind) -> {
            int power = strength + agility + mind;
            return "Total power of the hero " + power;
        };
        ThirdFunction<String, Integer, Integer, String> infoAboutHero = (String name, Integer age, Integer level)
                -> "Info hero: " + name +
                " Age: " + age +
                " Level: " + level;
        System.out.println(countStrength.apply(3, 7, 8));
        System.out.println(infoAboutHero.apply("Vlad", 7, 17));

    }
}
