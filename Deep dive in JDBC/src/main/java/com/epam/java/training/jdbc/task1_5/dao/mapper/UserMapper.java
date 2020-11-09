package com.epam.java.training.jdbc.task1_5.dao.mapper;

import com.epam.java.training.jdbc.task1_5.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserMapper implements RowMapper {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setName(rs.getString("name"));
        user.setSurname(rs.getString("surname"));
        user.setBirthDate(rs.getDate("birth_date"));
        return user;
    }
}
