package com.epam.java.training.lambda.task1;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Thread.sleep;

public class Main {


    public static void main(String[] args) {
        //Fibonacci numbers
        Thread thread1 = new Thread(
                () -> Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], n[0] + n[1]})
                        .limit(20)
                        .map(n -> n[0])
                        .forEach(x -> {
                            try {
                                sleep(1000);
                                System.out.println(x);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }));
        int sum = Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], n[0] + n[1]})
                .limit(20)
                .map(n -> n[0])
                .mapToInt(n -> n)
                .sum();
        System.out.println(sum);
        List<Integer> arrayList = Arrays.asList(32, 234, 324, 234);
        //2 Thread for 4 numbers
        Thread thread2 = new Thread(
                () -> arrayList.stream()
                        .limit(4)
                        .forEach((x) -> {
                            try {
                                sleep(3000);
                                System.out.println(x);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        })
        );
        thread1.start();
        thread2.start();
        System.out.println("Main thread finished!");
    }
}
