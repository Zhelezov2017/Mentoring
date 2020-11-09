package com.epam.java.training.concurrency.task1;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Vladislav Zhelezov.
 */
public class Main2 {

    //Collections.synchronizedMap add Synchronized
    private static Map<Integer, Integer> map1 = Collections.synchronizedMap(new HashMap<>());
    //new ConcurrentHashMap from java.util.concurrent.ConcurrentHashMap
    private static Map<Integer, Integer> map2 = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        //fix ConcurrentModificationException
        //write in map
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                map2.put(i, i);
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
            for (int i = 0; i < 10; i++) {
                int sum = 0;
                for (Integer integer : map2.keySet()) {
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
