package com.epam.java.training.nosql.task3;


import com.epam.java.training.nosql.task3.service.MovieService;
import com.epam.java.training.nosql.task3.service.impl.MovieServiceImpl;

public class Main {


    public static void main(final String[] args) {
        MovieService movieService = new MovieServiceImpl();
        String ipAddress = args.length > 0 ? args[0] : "localhost";
        int port = args.length > 1 ? Integer.parseInt(args[1]) : 9042;
        System.out.println("Connecting to IP Address " + ipAddress + ":" + port + "...");
        movieService.connect(ipAddress, port);
        movieService.createTableMovie();
        movieService.close();
    }
}
