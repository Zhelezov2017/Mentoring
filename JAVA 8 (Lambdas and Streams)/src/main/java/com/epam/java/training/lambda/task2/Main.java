package com.epam.java.training.lambda.task2;

import com.epam.java.training.lambda.task2.model.Person;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Person> people = getPersons();
        Comparator<Person> comparatorByName = Comparator.comparing(Person::getName);
        Comparator<Person> comparatorByAge = Comparator.comparing(Person::getAge);

        System.out.print("Persons on the List of name: ");
        System.out.println();
        people.stream()
                .sorted(comparatorByName)
                .forEach(e -> System.out.println(e.toString() + "; "));
        System.out.println();

        System.out.print("Person with max age: ");
        people.stream()
                .max(comparatorByAge)
                .ifPresent(p -> System.out.print(p.getAge()));
        System.out.println();

        System.out.print("Third on the age: ");
        people.stream()
                .map(e -> e.getAge() + 11)
                .distinct()
                .collect(Collectors.toList())
                .stream()
                .skip(2)
                .findFirst()
                .ifPresent(System.out::print);
        System.out.println();

        System.out.println("People Are 48 year old: ");
        people.stream()
                .parallel()
                .filter(e -> e.getAge().equals(48))
                .forEach(p -> System.out.println(p + "; "));

        System.out.println("Name people: ");
        people.stream()
                .distinct()
                .collect(Collectors.toMap(Person::getAge, Person::getName))
                .values()
                .forEach(p -> System.out.println(p + " "));

        System.out.print("All People grouping by on age: ");
        people.stream()
                .sorted(comparatorByAge)
                .forEach(p -> System.out.print(p + "; "));

    }

    private static List<Person> getPersons() {
        return Arrays.asList(new Person("Vasya", 18), new Person("Oleg", 42),
                new Person("Dima", 41), new Person("Alexs", 48));
    }
}
