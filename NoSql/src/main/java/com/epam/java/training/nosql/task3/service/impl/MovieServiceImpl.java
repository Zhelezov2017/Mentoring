package com.epam.java.training.nosql.task3.service.impl;


import com.epam.java.training.nosql.task3.connector.CassandraConnector;
import com.epam.java.training.nosql.task3.entity.Movie;
import com.epam.java.training.nosql.task3.service.MovieService;

public class MovieServiceImpl implements MovieService {

    private final static String CREATE_KEY_SPACE = "CREATE KEYSPACE IF NOT EXISTS movies_keyspace WITH REPLICATION = { 'class' :" +
            " 'NetworkTopologyStrategy', 'datacenter1' : 3 };";
    private final static String CREATE_TABLE_MOVIES = "CREATE TABLE IF NOT EXISTS movies_keyspace.movies (title varchar, year int, description varchar, "
            + "mmpa_rating varchar, dustin_rating varchar, PRIMARY KEY (title, year))";
    private final static String INSERT_MOVIE = "INSERT INTO movies_keyspace.movies (title, year, description, mmpa_rating, dustin_rating) " +
            "VALUES (?, ?, ?, ?, ?)";
    private final CassandraConnector client = new CassandraConnector();


    @Override
    public void connect(String ipAddress, int port) {
        client.connect(ipAddress, port);
    }

    @Override
    public void createTableMovie() {
        client.getSession().execute(CREATE_KEY_SPACE);
        client.getSession().execute(CREATE_TABLE_MOVIES);
    }

    @Override
    public void saveMovie(Movie movie) {
        client.getSession().execute(INSERT_MOVIE, movie.getTitle(), movie.getYear(), movie.getDescription(),
                movie.getMmpaRating(), movie.getDustinRating());
    }

    @Override
    public void close() {
        client.close();
    }
}
