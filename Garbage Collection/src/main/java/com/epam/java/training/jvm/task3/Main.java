package com.epam.java.training.jvm.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Thread.sleep;

/**
 * Created by Vladislav Zhelezov.
 */
public class Main {

    private static List<String> listThreadBig = new ArrayList<>(30000 * 30000);
    private static List<String> listThreadMiddle = new ArrayList<>(20000 * 20000);
    private static List<String> listThreadVerySmall = new ArrayList<>(10000 * 10000);
    private static List<String> listThreadSmall = new ArrayList<>(15000 * 15000);

    //-XX:+HeapDumpOnOutOfMemoryError
    public static void main(String[] args) {
        //filling of lists
        Stream.iterate(1, x -> x + 1)
                .limit(1000)
                .forEach((x) -> {
                    try {
                        sleep(10);
                        Stream.iterate(1, y -> y + 1)
                                .limit(1000)
                                .forEach((y) -> {
                                    listThreadSmall.add("thread demon" + y);
                                    listThreadVerySmall.add("thread demon" + y);
                                    listThreadMiddle.add("thread demon" + y);
                                    listThreadBig.add("thread demon" + y);
                                });
                        System.out.println("Thread ready!" + x);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
    }
}
