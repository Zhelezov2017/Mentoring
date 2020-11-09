package com.epam.java.training.concurrency.task4;

import com.epam.java.training.concurrency.task4.model.BlockingObjectPool;

import java.util.Random;

/**
 * Created by Vladislav Zhelezov.
 */
public class Main {

    private static final int NUMBER_OF_READERS = 2;
    private static final int NUMBER_OF_WRITERS = 1;

    //Get Pool
    public static void main(String[] args) {
        BlockingObjectPool blockingObjectPool = new BlockingObjectPool(5);
        blockingObjectPool.take("Hi!");

        for (int i = 0; i < NUMBER_OF_WRITERS; i++) {
            getThreadMethodTake(blockingObjectPool, "Writer-" + i).start();
        }

        for (int i = 0; i < NUMBER_OF_READERS; i++) {
            getThreadMethodGet(blockingObjectPool, "Reader-" + i).start();
        }
    }

    private static Thread getThreadMethodGet(BlockingObjectPool blockingObjectPool, String threadName) {
        return new Thread(() -> {
            Thread.currentThread().setName(threadName);
            while (true) {
                try {
                    Object o = blockingObjectPool.get();
                    System.out.println(Thread.currentThread().getName() + ": " + o.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sleep(100L);
            }
        });
    }

    private static Thread getThreadMethodTake(BlockingObjectPool blockingObjectPool, String threadName) {
        return new Thread(() -> {
            Thread.currentThread().setName(threadName);
            while (true) {
                String newMessage = newMessage();
                blockingObjectPool.take(newMessage);
                System.out.println(Thread.currentThread().getName() + ": " + newMessage);
                sleep(100L);
            }
        });
    }

    private static String newMessage() {
        //random string length 10
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder(10);
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    private static void sleep(long timeToSleep) {
        try {
            Thread.sleep(timeToSleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
