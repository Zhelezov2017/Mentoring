package com.epam.java.training.jdbc.task1_5.dao.mapper;

import com.epam.java.training.jdbc.task1_5.model.Like;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LikeMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Like like = new Like();
        like.setPostId(rs.getLong("postId"));
        like.setUserId(rs.getLong("userId"));
        like.setTimestamp(rs.getTimestamp("timestamp"));
        return like;
    }
}
