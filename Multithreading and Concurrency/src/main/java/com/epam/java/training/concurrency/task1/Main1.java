package com.epam.java.training.concurrency.task1;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vladislav Zhelezov.
 */
public class Main1 {

    private static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        //catching ConcurrentModificationException
        //write in map
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                map.put(i, i);
                System.out.println("Положили: " + i);
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        //get from map
        new Thread(() -> {
            int sum = 0;
            for (int i = 0; i < 10; i++) {
                for (Integer integer : map.keySet()) {
                    sum += integer;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Получили sum: " + sum);
            }
        }).start();


    }
}
