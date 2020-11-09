package com.epam.java.training.concurrency.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by Vladislav Zhelezov.
 */
public class Main {

    private static final List<Integer> listNumbers = new ArrayList<>();

    public static void main(String[] args) {

        //random number
        Random random = new Random();

        //Add number
        runJob(() -> {
            while (true) {
                synchronized (listNumbers) {
                    int num = random.nextInt(10);
                    listNumbers.add(num);
                    System.out.println("Add number: " + num);
                    sleep(20L);
                }
            }
        });

        //Count sqrt from sum
        runJob(() -> {
            while (true) {
                synchronized (listNumbers) {
                    long sum = listNumbers.stream()
                            .mapToLong(n -> n * n)
                            .sum();
                    System.out.println("Sqrt from sum pow: " + Math.sqrt(sum));
                    sleep(10L);
                }
            }
        });

        runJob(() -> {
            while (true) {
                synchronized (listNumbers) {
                    long sum = listNumbers.stream()
                            .mapToLong(n -> n)
                            .sum();
                    System.out.println("Sum: " + sum);
                    sleep(15L);
                }
            }
        });

    }

    private static void sleep(long l) {
        try {
            Thread.sleep(l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void runJob(Runnable runnable) {
        new Thread(runnable).start();
    }
}
