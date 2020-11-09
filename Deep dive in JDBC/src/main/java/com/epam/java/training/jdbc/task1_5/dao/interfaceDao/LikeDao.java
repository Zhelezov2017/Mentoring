package com.epam.java.training.jdbc.task1_5.dao.interfaceDao;


import com.epam.java.training.jdbc.task1_5.model.Like;

import java.util.Date;

public interface LikeDao extends ObjectDao<Like> {

    void createLike(Long postId, Long userId, Date timestamp);

    Like getLikeById(Long postId, Long userId);

    void updateLike(Long postId, Long userId, Date timestamp);

    void removeLike(Long postId, Long userId);
}
