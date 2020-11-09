package com.epam.java.training.jvm.task1;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by Vladislav Zhelezov.
 */
public class MainSubsection1 {

    private final static String datePrefix = "VLADALEXANDER";
    private static Map<String, MainSubsection1> mapString = new HashMap<>(Integer.MAX_VALUE);
    private static boolean slowExecution = false;

    public static void main(String[] args) {
        try {
            //Fill Map
            Stream.iterate(1, x -> x + 1)
                    .forEach(
                            x -> {
                                mapString.put(datePrefix + x, new MainSubsection1());
                                System.out.println("Put string " + x);
                                if (slowExecution) {
                                    try {
                                        Thread.sleep(10);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                            });
        } catch (Throwable throwable) {
            System.out.println("MainSubsection1 failure " + throwable);
        }
        System.out.println("--End!--");

    }
}
