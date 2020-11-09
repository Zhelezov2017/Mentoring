package com.epam.java.trainings.counter;


import com.epam.java.trainings.counter.configuration.CounterConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.logging.Logger;

import static com.epam.java.trainings.counter.parameter.CounterConfigurationParameters.DATA_SOURCE_DRIVER;
import static com.epam.java.trainings.counter.parameter.CounterConfigurationParameters.DATA_SOURCE_PASSWORD;
import static com.epam.java.trainings.counter.parameter.CounterConfigurationParameters.DATA_SOURCE_URL;
import static com.epam.java.trainings.counter.parameter.CounterConfigurationParameters.DATA_SOURCE_USERNAME;


@Component
public class Counter {

    private CounterConfiguration counterConfiguration;

    private static final Logger log = Logger.getLogger(Counter.class.getName());
    private static final String SELECT_AMOUNT_OF_OBJECTS = "SELECT COUNT(*) FROM USER_OBJECTS";


    public Counter(CounterConfiguration counterConfiguration) {
        this.counterConfiguration = counterConfiguration;
        this.jdbcTemplate = new JdbcTemplate(oracleDataSource());
    }

    private JdbcTemplate jdbcTemplate;



    private Long amountOfObjects() {

        return jdbcTemplate.queryForObject(SELECT_AMOUNT_OF_OBJECTS, Long.class);
    }

    @PostConstruct
    public void bigDataProject() {
        if (amountOfObjects() > 1000) {
            log.info("It’s BigData project");
            System.exit(0);
        } else {
            log.info("It's not BigData project");
        }
    }


    private DataSource oracleDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(counterConfiguration.getProperty(DATA_SOURCE_DRIVER));
        dataSource.setUrl(counterConfiguration.getProperty(DATA_SOURCE_URL));
        dataSource.setUsername(counterConfiguration.getProperty(DATA_SOURCE_USERNAME));
        dataSource.setPassword(counterConfiguration.getProperty(DATA_SOURCE_PASSWORD));

        return dataSource;
    }
}
