package com.epam.java.training.jdbc.task1_5;


import com.epam.java.training.jdbc.task1_5.dao.impl.UserDaoImpl;
import com.epam.java.training.jdbc.task1_5.dao.interfaceDao.UserDao;
import com.epam.java.training.jdbc.task1_5.model.User;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class Main1 {
    //need to change on own
    private static final String PATH = "C:\\Users\\Vladislav_Zhelezov\\EPAM\\Mentoring\\Java\\vladislav-zhelezov" +
            "\\Deep dive in JDBC\\";
    public static final String URL = "url";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String DRIVER_CLASS_NAME = "oracle.jdbc.driver.OracleDriver";



    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
        userDao.setDataSource(dataSource());
        List<User> users = userDao.getUsersWhereMoreFriendsAndLikesInMonth(100L, 100L);
        users.forEach(user -> System.out.println(user.toString()));
    }

    public static DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(PATH + "src\\main\\resources\\dbsettings.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dataSource.setDriverClassName(DRIVER_CLASS_NAME);
        dataSource.setUrl(properties.getProperty(URL));
        dataSource.setUsername(properties.getProperty(USERNAME));
        dataSource.setPassword(properties.getProperty(PASSWORD));
        return dataSource;
    }

}

