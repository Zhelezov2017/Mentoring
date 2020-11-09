package com.epam.java.training.jdbc.task1_5.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static com.epam.java.training.jdbc.task1_5.Main1.dataSource;

@Configuration
@PropertySource("dbsettings.properties")
@EnableTransactionManagement
public class ConfigurationJdbc {

    private final JdbcTemplate jdbcTemplate;

    public ConfigurationJdbc() {
        this.jdbcTemplate = new JdbcTemplate(dataSource());
    }


    public void createTable() {
        try {
            jdbcTemplate.execute("create table users(" +
                    "id NUMBER PRIMARY KEY, name varchar(255), surname varchar(255), birthDate DATE DEFAULT (sysdate))");
            jdbcTemplate.execute("create table LIKES(" +
                    "postid NUMBER, userid NUMBER, timestamp DATE DEFAULT (sysdate))");
            jdbcTemplate.execute("create table POSTS(" +
                    "id NUMBER PRIMARY KEY, userId NUMBER, text varchar(255), timestamp DATE DEFAULT (sysdate))");
            jdbcTemplate.execute("create table FRIENDSHIPS(" +
                    "userId1 Number, userId2 Number, timestamp DATE DEFAULT (sysdate))");
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public void dropTable() {
        try {
            jdbcTemplate.execute("drop table USERS");
            jdbcTemplate.execute("drop table LIKES");
            jdbcTemplate.execute("drop table POSTS");
            jdbcTemplate.execute("drop table FRIENDSHIPS");
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
}
