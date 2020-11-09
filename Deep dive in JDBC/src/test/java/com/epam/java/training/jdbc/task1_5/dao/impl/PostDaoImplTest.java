package com.epam.java.training.jdbc.task1_5.dao.impl;

import com.epam.java.training.jdbc.task1_5.dao.interfaceDao.PostDao;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

import static com.epam.java.training.jdbc.task1_5.Main1.dataSource;


public class PostDaoImplTest {

    private PostDao service = new PostDaoImpl();

    @Test
    public void testCreateProcedurePostWithoutRollBack() throws SQLException {
        service.setDataSource(dataSource());
        Assert.assertFalse(service.createProcedurePost());
    }
}
