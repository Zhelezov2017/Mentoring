package com.epam.java.training.jdbc.task1_5.dao.interfaceDao;


import com.epam.java.training.jdbc.task1_5.model.Friendship;

import java.util.Date;

public interface FriendshipDao extends ObjectDao<Friendship> {

    void createFriendship(Long userId1, Long userId2, Date timestamp);

    Friendship getFriendshipById(Long userId1, Long userId2);

    void updateFriendship(Long userId1, Long userId2, Date timestamp);

    void removeFriendship(Long userId1, Long userId2);

}
