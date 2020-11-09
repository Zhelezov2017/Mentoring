package com.epam.java.training.jvm.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Thread.sleep;

/**
 * Created by Vladislav Zhelezov.
 */
public class Main {
    //List String
    private static List<String> strings = new ArrayList<>();

    //task1 -Xms1760m -Xmx1760m -Xmn880m -XX:SurvivorRatio=3 -XX:+UseConcMarkSweepGC
    //task2 -Xms512m -Xmx512m -Xmn256m -XX:SurvivorRatio=2 -XX:+UseConcMarkSweepGC
    //task3 -Xms512m -Xmx512m -Xmn384m  -XX:SurvivorRatio=3 -XX:+UseConcMarkSweepGC
    //task4 -Xms768m -Xmx768m -Xmn640m  -XX:SurvivorRatio=3 -XX:+UseConcMarkSweepGC
    public static void main(String[] args) {
        Stream.iterate(1, x -> x + 1)
                .limit(10000)
                .forEach((x) -> {
                    try {
                        sleep(30);
                        Stream.iterate(1, y -> y + 1)
                                .limit(1000)
                                .forEach((y) -> strings.add("thread main" + y));
                        System.out.println("Thread main ready!" + x);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
    }
}
