package com.epam.java.training.jdbc.task1_5.dao.interfaceDao;


import com.epam.java.training.jdbc.task1_5.model.Post;

import java.sql.SQLException;
import java.util.Date;

public interface PostDao extends ObjectDao<Post> {

    void createPost(Long id, Long userId, String text, Date timestamp);

    Post getPostById(Long id);

    void updatePost(Long id, Long userId, String text, Date timestamp);

    Post getMostPopularPost();

    Boolean createProcedurePost() throws SQLException;

    void removePost(Long id);
}
