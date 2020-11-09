package com.epam.java.training.jdbc.task1_5.dao.mapper;

import com.epam.java.training.jdbc.task1_5.model.Post;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostMapper implements RowMapper  {
    @Override
    public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
        Post post = new Post();
        post.setId(rs.getLong("id"));
        post.setUserId(rs.getLong("userId"));
        post.setText(rs.getString("text"));
        post.setTimestamp(rs.getTimestamp("timestamp"));
        return post;
    }
}
