package com.epam.java.training.spring_boot.task1;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    //-Dspring.profiles.active=production, debug
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
