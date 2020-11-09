package com.epam.java.training.jdbc.task1_5.dao.impl;

import com.epam.java.training.jdbc.task1_5.dao.interfaceDao.UserDao;
import com.epam.java.training.jdbc.task1_5.dao.mapper.UserMapper;
import com.epam.java.training.jdbc.task1_5.model.User;
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

import static com.epam.java.training.jdbc.task1_5.dao.impl.PostDaoImpl.getLong;

public class UserDaoImpl implements UserDao {

    private static final String SQL_INSERT_USER = "INSERT INTO USERS(id, name, surname, birth_Date) VALUES (?,?,?,?)";
    private static final String SQL_SELECT_USER = "SELECT * FROM USERS WHERE id = ?";
    private static final String SQL_UPDATE_USER = "UPDATE USERS SET name = ?, surname = ?, birth_Date = ? WHERE id = ?";
    private static final String SQL_DELETE_USER = "DELETE FROM USERS WHERE id = ?";
    private static final String SQL_WHERE_MORE_FRIENDS_AND_LIKES = "SELECT * FROM USERS u WHERE u.ID IN " +
            "(SELECT USER_ID FROM (SELECT DISTINCT POSTS.USER_ID, COUNT(LIKES.USER_ID) AS likes_count FROM POSTS " +
            "            INNER JOIN LIKES ON POSTS.ID = LIKES.POST_ID " +
            "                    WHERE LIKES.\"TIMESTAMP\" BETWEEN TO_DATE('2025-03-01','RRRR-MM-DD') " +
            "                            AND TO_DATE('2025-09-01','RRRR-MM-DD') " +
            "                            GROUP BY POSTS.USER_ID) " +
            "                    WHERE likes_count > ?) AND " +
            "             u.ID IN (SELECT ID FROM( SELECT DISTINCT ID, COUNT(ID) AS friendships_count FROM " +
            "           (SELECT * FROM USERS u INNER JOIN FRIENDSHIPS ON u.ID IN " +
            "                   (FRIENDSHIPS.USER_ID1, FRIENDSHIPS.USER_ID2)) GROUP BY ID) WHERE friendships_count > ?)";
    private static final String SQL_CREATE_PROCEDURE_USER = "CREATE OR REPLACE PROCEDURE JAVA_MENTORING.getMostPopularUserByLikes(" +
            " P_ID OUT NUMBER, P_NAME OUT VARCHAR2, P_SURNAME OUT VARCHAR2, P_BIRTH_DATE OUT DATE) AS" +
            " BEGIN" +
            " SELECT u.ID, u.NAME, u.SURNAME, u.BIRTH_DATE INTO P_ID, P_NAME, P_SURNAME, P_BIRTH_DATE FROM USERS u" +
            " WHERE u.ID IN (SELECT USER_ID FROM (" +
            " SELECT DISTINCT POSTS.USER_ID, COUNT(LIKES.USER_ID) AS likes_count FROM POSTS" +
            " INNER JOIN LIKES ON POSTS.ID = LIKES.POST_ID" +
            " WHERE LIKES.TIMESTAMP BETWEEN TO_DATE('2025-03-01','RRRR-MM-DD')" +
            " AND TO_DATE('2025-09-01','RRRR-MM-DD')" +
            " GROUP BY POSTS.USER_ID)" +
            " WHERE likes_count > 100) AND" +
            " u.ID IN (SELECT ID FROM( SELECT DISTINCT ID, COUNT(ID) AS friendships_count FROM" +
            " (SELECT * FROM USERS u INNER JOIN FRIENDSHIPS ON u.ID IN" +
            " (FRIENDSHIPS.USER_ID1, FRIENDSHIPS.USER_ID2)) GROUP BY ID) WHERE friendships_count > 100) FETCH FIRST 1 ROWS ONLY;" +
            " END;";

    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void createUser(Long id, String name, String surname, Date birthDate) {
        jdbcTemplate.update(SQL_INSERT_USER, id, name, surname, birthDate);
    }

    @Override
    public User getUserById(Long id) {
        return (User) jdbcTemplate.queryForObject(SQL_SELECT_USER, new Object[]{id}, new UserMapper());
    }

    @Override
    public void updateUser(Long id, String name, String surname, Date birthDate) {
        jdbcTemplate.update(SQL_UPDATE_USER, name, surname, birthDate, id);
    }

    @Override
    public List<User> getUsersWhereMoreFriendsAndLikesInMonth(Long friends, Long likes) {
        return jdbcTemplate.query(SQL_WHERE_MORE_FRIENDS_AND_LIKES, new Object[]{likes, friends}, new UserMapper());
    }

    @Override
    public User getMostPopularUserByLike() {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("getMostPopularUserByLikes");
        SqlParameterSource in = new MapSqlParameterSource();
        Map<String, Object> simpleJdbcCallResult = jdbcCall.execute(in);
        User user = new User();
        user.setId(getLong((BigDecimal) simpleJdbcCallResult.get("P_ID")));
        user.setName((String) simpleJdbcCallResult.get("P_NAME"));
        user.setSurname((String) simpleJdbcCallResult.get("P_SURNAME"));
        user.setBirthDate(getDate((Timestamp) simpleJdbcCallResult.get("P_BIRTH_DATE")));

        return user;
    }

    @Override
    public Boolean createProcedureUser() throws SQLException {
        Connection con = jdbcTemplate.getDataSource().getConnection();
        System.out.println("Connection established......");
        //Creating the Statement
        Statement stmt = con.createStatement();
        //Executing the query
        return stmt.execute(SQL_CREATE_PROCEDURE_USER);
    }

    public static java.sql.Date getDate(Timestamp timestamp){
        return new java.sql.Date(timestamp.getTime());
    }


    @Override
    public void removeUser(Long id) {
        jdbcTemplate.update(SQL_DELETE_USER, id);
    }

    @Override
    public int[] batchUpdateUsingJdbcTemplate(final List<User> users) {
        return jdbcTemplate.batchUpdate(SQL_INSERT_USER, new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setLong(1, users.get(i).getId());
                        ps.setString(2, users.get(i).getName());
                        ps.setString(3, users.get(i).getSurname());
                        ps.setDate(4, users.get(i).getBirthDate());
                    }

                    @Override
                    public int getBatchSize() {
                        return users.size();
                    }
                });
    }
}
