package com.epam.java.training.jvm.task2.model;

import com.epam.java.training.jvm.task4.interfaceRealize.Animal;

/**
 * Created by Vladislav Zhelezov.
 */
public class Dog implements Animal {

    @Override
    public void play() {
        System.out.println("I play with dog!");
    }

    @Override
    public void voice() {
        System.out.println("Woof!");
    }

    @Override
    public String toString() {
        return "Dog{}";
    }
}
