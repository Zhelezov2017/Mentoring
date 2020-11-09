package com.epam.java.training.jvm.task5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Thread.sleep;

/**
 * Created by Vladislav Zhelezov.
 */
public class Main {

    private static List<String> listThreadMain = new ArrayList<>();
    private static List<String> listThreadDemon = new ArrayList<>();

    //With conventional paramaters 636 objects -Xmx512m
    //With String Deduplication 997 objects -Xmx512m
    public static void main(String[] args) {
        //2 threads for include in list
        Thread thread = new Thread(
                () -> Stream.iterate(1, x -> x + 1)
                        .limit(10000)
                        .forEach((x) -> {
                            try {
                                sleep(10);
                                Stream.iterate(1, y -> y + 1)
                                        .limit(10000)
                                        .forEach((y) -> {
                                            listThreadDemon.add("thread demon" + y);
                                            listThreadMain.add("thread main" + y);
                                        });
                                System.out.println("Thread demon ready!" + x);
                                System.out.println("Thread demon in main ready!" + x);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }));
        thread.start();
        Stream.iterate(1, x -> x + 1)
                .limit(1000)
                .forEach((x) -> {
                    try {
                        sleep(10);
                        Stream.iterate(1, y -> y + 1)
                                .limit(1000)
                                .forEach((y) -> listThreadMain.add("thread main" + y));
                        System.out.println("Thread main ready!" + x);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
    }
}
