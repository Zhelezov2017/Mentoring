package com.epam.java.training.concurrency.task3.service;


import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class MsgBusImpl implements MsgBus {

    private final Map<String, AsyncQueue<String>> topicToQueueMap = new HashMap<>();

    @Override
    public void send(String msg, String topic) {
        AsyncQueue<String> queue = getQueue(topic);
        queue.add(msg);
    }

    @Override
    public void receive(String topic, Consumer<String> onMsgReceivedCallback) {
        AsyncQueue<String> queue = getQueue(topic);
        queue.get(onMsgReceivedCallback);
    }

    private synchronized AsyncQueue<String> getQueue(String topic) {
        return topicToQueueMap.computeIfAbsent(topic, t -> new AsyncQueue<>());
    }
}
