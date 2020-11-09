package com.epam.java.training.jdbc.task1_5.dao.interfaceDao;


import javax.sql.DataSource;
import java.util.List;

public interface ObjectDao<T> {

    void setDataSource(DataSource dataSource);

    int[] batchUpdateUsingJdbcTemplate(List<T> object);

}
