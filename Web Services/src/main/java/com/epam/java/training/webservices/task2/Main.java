package com.epam.java.training.webservices.task2;


import com.epam.java.training.webservices.task2.service.impl.FileServiceImpl;

import javax.xml.ws.Endpoint;

public class Main {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8081/files",
                new FileServiceImpl());
    }
}
