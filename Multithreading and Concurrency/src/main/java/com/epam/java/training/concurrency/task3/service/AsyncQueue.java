package com.epam.java.training.concurrency.task3.service;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.function.Consumer;

public class AsyncQueue<T> {
    private final Queue<T> elements = new ArrayDeque<>();
    private final Queue<Consumer<T>> callbacks = new ArrayDeque<>();

    public synchronized void add(T e) {
        if (callbacks.size() > 0) {
            callbacks.remove().accept(e);
        } else {
            elements.offer(e);
        }
    }

    public synchronized void get(Consumer<T> callback) {
        if (elements.size() > 0) {
            callback.accept(elements.remove());
        } else {
            callbacks.offer(callback);
        }
    }
}
