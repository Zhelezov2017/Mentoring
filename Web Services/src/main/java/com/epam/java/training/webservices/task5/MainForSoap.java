package com.epam.java.training.webservices.task5;

import com.epam.java.training.webservices.task5.service.impl.UserServiceImpl;

import javax.xml.ws.Endpoint;


public class MainForSoap {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8081/users",
                new UserServiceImpl());
    }
}
