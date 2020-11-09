package com.epam.java.training.jdbc.task1_5.dao.impl;

import com.epam.java.training.jdbc.task1_5.dao.interfaceDao.PostDao;
import com.epam.java.training.jdbc.task1_5.dao.mapper.PostMapper;
import com.epam.java.training.jdbc.task1_5.model.Post;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PostDaoImpl implements PostDao {

    private static final String SQL_INSERT_POST = "INSERT INTO POSTS(id, userId, text, timestamp) VALUES (?,?,?,?)";
    private static final String SQL_SELECT_POST = "SELECT * FROM USERS WHERE id = ?";
    private static final String SQL_UPDATE_POST = "UPDATE USERS SET name = ?, surname = ?, birthDate = ? WHERE id = ?";
    private static final String SQL_DELETE_POST = "UPDATE USERS SET name = ?, surname = ?, birthDate = ? WHERE id = ?";
    private static final String SQL_GET_ALL_PROCEDURES = "SELECT owner, object_name " +
            "FROM dba_objects " +
            "WHERE object_type = 'PROCEDURE'";
    private static final String SQL_CREATE_PROCEDURE_POST = "CREATE OR REPLACE PROCEDURE JAVA_MENTORING.getMostPopularPost(" +
            " P_ID OUT NUMBER, P_USER_ID OUT NUMBER, P_TEXT OUT VARCHAR2, P_TIMESTAMP OUT TIMESTAMP, P_LIKES_COUNT OUT NUMBER) AS" +
            " BEGIN" +
            " SELECT POSTS.ID, POSTS.USER_ID, POSTS.TEXT, POSTS.TIMESTAMP, COUNT(LIKES.USER_ID) AS likes_count INTO P_ID," +
            " P_USER_ID, P_TEXT, P_TIMESTAMP, P_LIKES_COUNT FROM POSTS" +
            " INNER JOIN LIKES ON POSTS.ID = LIKES.POST_ID" +
            " GROUP BY POSTS.ID, POSTS.USER_ID, POSTS.TEXT, POSTS.TIMESTAMP ORDER BY likes_count DESC FETCH FIRST 1 ROWS ONLY;" +
            " END;";

    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void createPost(Long id, Long userId, String text, Date timestamp) {
        jdbcTemplate.update(SQL_INSERT_POST, id, userId, text, timestamp);
    }

    @Override
    public Post getPostById(Long id) {
        return (Post) jdbcTemplate.queryForObject(SQL_SELECT_POST, new Object[]{id}, new PostMapper());
    }

    @Override
    public void updatePost(Long id, Long userId, String text, Date timestamp) {
        jdbcTemplate.update(SQL_UPDATE_POST, userId, text, timestamp, id);
    }

    @Override
    public Post getMostPopularPost() {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("getMostPopularPost");
        SqlParameterSource in = new MapSqlParameterSource();
        Map<String, Object> simpleJdbcCallResult = jdbcCall.execute(in);
        Post post = new Post();
        post.setId(getLong((BigDecimal) simpleJdbcCallResult.get("P_ID")));
        post.setUserId(getLong((BigDecimal) simpleJdbcCallResult.get("P_USER_ID")));
        post.setText((String) simpleJdbcCallResult.get("P_TEXT"));
        post.setTimestamp((Timestamp) simpleJdbcCallResult.get("P_TIMESTAMP"));

        return post;
    }

    @Override
    public Boolean createProcedurePost() throws SQLException {
        Connection con = jdbcTemplate.getDataSource().getConnection();
        System.out.println("Connection established......");
        //Creating the Statement
        Statement stmt = con.createStatement();
        //Executing the query
        return stmt.execute(SQL_CREATE_PROCEDURE_POST);
    }

    public static Long getLong(BigDecimal bigDecimal){
        return bigDecimal.longValue();
    }

    @Override
    public void removePost(Long id) {
        jdbcTemplate.update(SQL_DELETE_POST, id);
    }

    @Override
    public int[] batchUpdateUsingJdbcTemplate(final List<Post> posts) {
        return jdbcTemplate.batchUpdate(SQL_INSERT_POST,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setLong(1, posts.get(i).getId());
                        ps.setLong(2, posts.get(i).getUserId());
                        ps.setString(3, posts.get(i).getText());
                        ps.setTimestamp(4, posts.get(i).getTimestamp());
                    }

                    @Override
                    public int getBatchSize() {
                        return posts.size();
                    }
                });
    }
}
