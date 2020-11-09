package com.epam.java.training.jdbc.task1_5.dao.impl;

import com.epam.java.training.jdbc.task1_5.dao.interfaceDao.LikeDao;
import com.epam.java.training.jdbc.task1_5.dao.mapper.LikeMapper;
import com.epam.java.training.jdbc.task1_5.model.Like;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class LikeDaoImpl implements LikeDao {

    private static final String SQL_INSERT_LIKE = "INSERT INTO LIKES(postId, userId, timestamp) VALUES (?,?,?)";
    private static final String SQL_SELECT_LIKE = "SELECT * FROM LIKES WHERE postId = ? AND userId = ?";
    private static final String SQL_UPDATE_LIKE = "UPDATE LIKES SET timestamp = ? WHERE postId = ? AND userId = ?";
    private static final String SQL_DELETE_LIKE = "DELETE FROM LIKES WHERE postId = ? AND userId = ?";

    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void createLike(Long postId, Long userId, Date timestamp) {
        jdbcTemplate.update(SQL_INSERT_LIKE, postId, userId, timestamp);
    }

    @Override
    public Like getLikeById(Long postId, Long userId) {
        return (Like) jdbcTemplate.queryForObject(SQL_SELECT_LIKE, new Object[]{postId, userId}, new LikeMapper());
    }

    @Override
    public void updateLike(Long postId, Long userId, Date timestamp) {
        jdbcTemplate.update(SQL_UPDATE_LIKE, timestamp, postId, userId);
    }

    @Override
    public void removeLike(Long postId, Long userId) {
        jdbcTemplate.update(SQL_DELETE_LIKE, postId, userId);
    }

    @Override
    public int[] batchUpdateUsingJdbcTemplate(final List<Like> likes) {
        return jdbcTemplate.batchUpdate(SQL_INSERT_LIKE,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setLong(1, likes.get(i).getPostId());
                        ps.setLong(2, likes.get(i).getUserId());
                        ps.setTimestamp(3, likes.get(i).getTimestamp());
                    }
                    @Override
                    public int getBatchSize() {
                        return likes.size();
                    }
                });
    }
}
