package com.epam.java.training.jdbc.task1_5.dao.interfaceDao;

import com.epam.java.training.jdbc.task1_5.model.User;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface UserDao extends ObjectDao<User> {

    void createUser(Long id, String name, String surname, Date birthDate);

    User getUserById(Long id);

    void updateUser(Long id, String name, String surname, Date birthDate);

    List<User> getUsersWhereMoreFriendsAndLikesInMonth(Long friends, Long likes);

    User getMostPopularUserByLike();

    Boolean createProcedureUser() throws SQLException;

    void removeUser(Long id);
}
