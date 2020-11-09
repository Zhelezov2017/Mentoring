package com.epam.java.training.jdbc.task1_5;


import com.epam.java.training.jdbc.task1_5.dao.impl.PostDaoImpl;
import com.epam.java.training.jdbc.task1_5.dao.impl.UserDaoImpl;
import com.epam.java.training.jdbc.task1_5.dao.interfaceDao.PostDao;
import com.epam.java.training.jdbc.task1_5.dao.interfaceDao.UserDao;

import static com.epam.java.training.jdbc.task1_5.Main1.dataSource;

public class Main5 {

    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
        PostDao postDao = new PostDaoImpl();
        userDao.setDataSource(dataSource());
        postDao.setDataSource(dataSource());
        System.out.println(postDao.getMostPopularPost());
        System.out.println(userDao.getMostPopularUserByLike());
    }
}
