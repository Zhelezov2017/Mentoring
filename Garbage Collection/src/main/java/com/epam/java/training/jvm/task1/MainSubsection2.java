package com.epam.java.training.jvm.task1;


/**
 * Created by Vladislav Zhelezov.
 */
public class MainSubsection2 {


    // -Xmx10m
    public static void main(String[] args) throws Exception {
        while (true) {
            new Thread(() -> {
                try {
                    System.out.println("Starting new thread " + Thread.currentThread().getName());
                    Thread.sleep(1_000_000_000);
                    System.out.println("Thread " + Thread.currentThread().getName() + " is stopped");
                } catch (InterruptedException e) {
                }
            }).start();
        }
    }


}
