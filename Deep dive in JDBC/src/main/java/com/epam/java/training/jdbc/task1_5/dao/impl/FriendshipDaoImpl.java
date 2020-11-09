package com.epam.java.training.jdbc.task1_5.dao.impl;

import com.epam.java.training.jdbc.task1_5.dao.interfaceDao.FriendshipDao;
import com.epam.java.training.jdbc.task1_5.dao.mapper.FriendshipMapper;
import com.epam.java.training.jdbc.task1_5.model.Friendship;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class FriendshipDaoImpl implements FriendshipDao {

    private static final String SQL_INSERT_FRIENDSHIP = "INSERT INTO FRIENDSHIPS(userId1, userId2, timestamp) VALUES (?,?,?)";
    private static final String SQL_SELECT_FRIENDSHIP = "SELECT * FROM FRIENDSHIPS WHERE userId1 = ? AND userId2 = ?";
    private static final String SQL_UPDATE_FRIENDSHIP = "UPDATE LIKES SET timestamp = ? WHERE userId1 = ? AND userId2 = ?";
    private static final String SQL_DELETE_FRIENDSHIP = "DELETE FROM FRIENDSHIPS WHERE userId1 = ? AND userId2 = ?";

    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void createFriendship(Long userId1, Long userId2, Date timestamp) {
        jdbcTemplate.update(SQL_INSERT_FRIENDSHIP, userId1, userId2, timestamp);
    }

    @Override
    public Friendship getFriendshipById(Long userId1, Long userId2) {
        return (Friendship) jdbcTemplate.queryForObject(SQL_SELECT_FRIENDSHIP, new Object[]{userId1, userId2}, new FriendshipMapper());
    }

    @Override
    public void updateFriendship(Long userId1, Long userId2, Date timestamp) {
        jdbcTemplate.update(SQL_UPDATE_FRIENDSHIP, timestamp, userId1, userId2);
    }

    @Override
    public void removeFriendship(Long userId1, Long userId2) {
        jdbcTemplate.update(SQL_DELETE_FRIENDSHIP, userId1, userId2);
    }

    @Override
    public int[] batchUpdateUsingJdbcTemplate(final List<Friendship> friendships) {
        return jdbcTemplate.batchUpdate(SQL_INSERT_FRIENDSHIP, new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setLong(1, friendships.get(i).getUserId1());
                        ps.setLong(2, friendships.get(i).getUserId2());
                        ps.setTimestamp(3, friendships.get(i).getTimestamp());
                    }
                    @Override
                    public int getBatchSize() {
                        return friendships.size();
                    }
                });
    }
}
