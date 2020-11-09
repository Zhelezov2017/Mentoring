package com.epam.java.training.jdbc.task1_5.dao.impl;


import com.epam.java.training.jdbc.task1_5.dao.interfaceDao.UserDao;
import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;

import static com.epam.java.training.jdbc.task1_5.Main1.*;
import static org.dbunit.Assertion.assertEqualsIgnoreCols;

@RunWith(JUnit4.class)
public class UserDaoImplTest extends DataSourceBasedDBTestCase {
    //need to change on own
    private static final String PATH = "C:\\Users\\Vladislav_Zhelezov\\EPAM\\Mentoring\\Java\\vladislav-zhelezov" +
            "\\Deep dive in JDBC\\";
    private UserDao service = new UserDaoImpl();

    @Test
    public void testCreateUser() throws Exception {
        service.setDataSource(getDataSource());
        java.sql.Date birthDate = new java.sql.Date(1591549343698L);
        service.createUser(4000L, "Vlad", "Zhelezov", birthDate);

        ITable expectedTable = getDataSet().getTable("USERS");
        ITable actualData = getConnection()
                .createQueryTable("result", "SELECT * FROM USERS WHERE name='Vlad' AND ID = 4000");

        String[] ignore = {"id"};
        assertEqualsIgnoreCols(expectedTable, actualData, ignore);
    }

    @Test
    public void testGetUserById() throws Exception {
        service.setDataSource(getDataSource());
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(new FileInputStream(PATH + "src/test/resources/db/data/user-data-get-user-by-id.xml"));
        Connection conn = getDataSource().getConnection();
        //when
        conn.createStatement()
                .executeUpdate("INSERT INTO USERS(id, name, surname, birth_Date) " +
                        "VALUES ('1','Andrei','Koltsov',to_date('2020-06-07 20:02:23','RRRR-MM-DD HH24:MI:SS'))");
        service.getUserById(1L);

        ITable expectedTable = expectedDataSet.getTable("USERS");
        ITable actualData = getConnection()
                .createQueryTable("result", "SELECT * FROM USERS WHERE surname='Koltsov'");
        // then
        assertEqualsIgnoreCols(expectedTable, actualData, new String[]{"id"});
    }

    @Test
    public void testUpdateUser() throws Exception {
        service.setDataSource(getDataSource());
        java.sql.Date birthDate = new java.sql.Date(1591549343698L);
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(new FileInputStream(PATH + "src/test/resources/db/data/user-data-update.xml"));
        Connection conn = getDataSource().getConnection();
        //when
        conn.createStatement()
                .executeUpdate("INSERT INTO USERS(id, name, surname, birth_Date) " +
                        "VALUES ('5000','Andrei','Koltsov',to_date('2020-06-07 20:02:23','RRRR-MM-DD HH24:MI:SS'))");
        service.updateUser(5000L, "Ivan", "Naumov", birthDate);

        ITable expectedTable = expectedDataSet.getTable("USERS");
        ITable actualData = getConnection()
                .createQueryTable("result", "SELECT * FROM USERS WHERE surname='Naumov'");

        String[] ignore = {"id"};
        assertEqualsIgnoreCols(expectedTable, actualData, ignore);
    }

    @Test
    public void testRemoveUser() throws Exception {
        service.setDataSource(getDataSource());
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(new FileInputStream(PATH + "src/test/resources/db/data/user-data-remove.xml"));
        Connection conn = getDataSource().getConnection();
        //when
        conn.createStatement()
                .executeUpdate("INSERT INTO USERS(id, name, surname, birth_Date) " +
                        "VALUES ('1','Andrei','Koltsov',to_date('2020-06-07 20:02:23','RRRR-MM-DD HH24:MI:SS'))");
        service.removeUser(1L);

        ITable expectedTable = expectedDataSet.getTable("USERS");
        ITable actualData = getConnection()
                .createQueryTable("result", "SELECT * FROM USERS WHERE surname='Koltsov'");

        String[] ignore = {"id"};
        assertEqualsIgnoreCols(expectedTable, actualData, ignore);
    }

    @Test
    public void testBatchUpdateUsingJdbcTemplate() throws Exception {
        service.setDataSource(getDataSource());
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(new FileInputStream(PATH + "src/test/resources/db/data/user-data-batch.xml"));
        Connection conn = getDataSource().getConnection();
        //when
        conn.createStatement()
                .executeUpdate("INSERT INTO USERS(id, name, surname, birth_Date) " +
                        "VALUES ('1','Ivan','Naumov',to_date('2020-06-07 20:02:23','RRRR-MM-DD HH24:MI:SS'))");
        service.batchUpdateUsingJdbcTemplate(new ArrayList<>());

        ITable expectedTable = expectedDataSet.getTable("USERS");
        ITable actualData = getConnection()
                .createQueryTable("result", "SELECT * FROM USERS WHERE surname='Naumov'");

        String[] ignore = {"id"};
        assertEqualsIgnoreCols(expectedTable, actualData, ignore);
    }

    @Test
    public void testCreateProcedureUser() throws Exception {
        service.setDataSource(dataSource());
        Assert.assertFalse(service.createProcedureUser());
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        classLoader.getResource("db/data/user-data-save.xml");
        return new FlatXmlDataSetBuilder().build(new FileInputStream(PATH + "src/test/resources/db/data/user-data-save.xml"));
    }

    @Override
    protected DataSource getDataSource() {
        Properties properties = new Properties();
        try {
            properties.load(Objects.requireNonNull(Thread.currentThread()
                    .getContextClassLoader().getResourceAsStream("dbsettings.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER_CLASS_NAME);
        dataSource.setUsername(properties.getProperty(USERNAME));
        dataSource.setPassword(properties.getProperty(PASSWORD));
        dataSource.setUrl(properties.getProperty(URL));
        return dataSource;
    }

    @Override
    protected DatabaseOperation getSetUpOperation() {
        return DatabaseOperation.REFRESH;
    }

    @Override
    protected DatabaseOperation getTearDownOperation() {
        return DatabaseOperation.DELETE_ALL;
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        Connection connection = getConnection().getConnection();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
