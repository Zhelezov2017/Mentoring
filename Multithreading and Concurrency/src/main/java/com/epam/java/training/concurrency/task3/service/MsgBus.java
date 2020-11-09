package com.epam.java.training.concurrency.task3.service;

import java.util.function.Consumer;

public interface MsgBus {
    void send(String msg, String topic);

    void receive(String topic, Consumer<String> onMsgReceivedCallback);
}
