package com.epam.java.training.jdbc.task1_5.dao.mapper;

import com.epam.java.training.jdbc.task1_5.model.Friendship;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FriendshipMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Friendship friendship = new Friendship();
        friendship.setUserId1(rs.getLong("userId1"));
        friendship.setUserId2(rs.getLong("userId2"));
        friendship.setTimestamp(rs.getTimestamp("timestamp"));
        return friendship;
    }
}
