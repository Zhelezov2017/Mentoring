package com.epam.java.training.nosql.task3.service;


import com.epam.java.training.nosql.task3.entity.Movie;

public interface MovieService {

    void connect(String ipAddress, int port);

    void createTableMovie();

    void saveMovie(Movie movie);

    void close();
}
