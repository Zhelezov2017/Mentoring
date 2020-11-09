package com.epam.java.training.concurrency.task3;

import com.epam.java.training.concurrency.task3.service.MsgBus;
import com.epam.java.training.concurrency.task3.service.MsgBusImpl;

import java.util.Random;

/**
 * Created by Vladislav Zhelezov.
 */
public class Main {

    public static void main(String[] args) {
        MsgBus msgBus = new MsgBusImpl();

        String javaTopic = "JAVA";
        String pythonTopic = "PYTHON";

        //write in Bus
        writeInBus(msgBus, javaTopic, "Java-Writer-1");
        writeInBus(msgBus, javaTopic, "Java-Writer-2");
        writeInBus(msgBus, pythonTopic, "Python-Writer-1");

        //read bus
        readFromBus(msgBus, javaTopic, "Java-Reader-1");
        readFromBus(msgBus, pythonTopic, "Python-Reader-1");
        readFromBus(msgBus, pythonTopic, "Python-Reader-2");

    }

    private static void readFromBus(MsgBus msgBus, String javaTopic, String threadName) {
        newThread(() -> {
            Random random = new Random();
            Thread.currentThread().setName(threadName);
            while (true) {
                msgBus.receive(javaTopic, msg -> System.out.println(Thread.currentThread().getName() + ": " + msg));
                sleep(random.nextInt(100));
            }
        });
    }

    private static void writeInBus(MsgBus msgBus, String javaTopic, String threadName) {
        newThread(() -> {
            Random random = new Random();
            Thread.currentThread().setName(threadName);
            while (true) {
                String randMsg = newMessage();
                msgBus.send(randMsg, javaTopic);
                System.out.println(Thread.currentThread().getName() + ": " + randMsg);
                sleep(random.nextInt(100));
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

    private static void newThread(Runnable runnable) {
        new Thread(runnable).start();
    }

    private static void sleep(int nextInt) {
        try {
            Thread.sleep(nextInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
