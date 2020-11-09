package com.epam.java.training.hibernate.task1.service.impl;


import com.epam.java.training.hibernate.task1.data.entity.Post;
import com.epam.java.training.hibernate.task1.service.MarketService;

import javax.persistence.*;
import java.util.List;

import static com.epam.java.training.hibernate.task2.configuration.EntityManagerConfiguration.PERSISTENT_UNIT_NAME;

public class PostServiceImpl implements MarketService<Post> {

    private static final String SELECT_LIST_POSTS = "SELECT post from POST post";

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
    @PersistenceContext
    private EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void save(Post post) {
        entityManager.getTransaction().begin();
        entityManager.persist(post);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Post> findAll() {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery(SELECT_LIST_POSTS);
        entityManager.getTransaction().commit();
        return query.getResultList();
    }

    @Override
    public Post findById(Long id) {
        entityManager.getTransaction().begin();
        Post post = entityManager.find(Post.class, id);
        entityManager.getTransaction().commit();
        return post;
    }

    @Override
    public Post update(Post post) {
        entityManager.getTransaction().begin();
        Post mergePost = entityManager.merge(post);
        entityManager.getTransaction().commit();
        return mergePost;
    }

    @Override
    public void delete(Long id) {
        entityManager.getTransaction().begin();
        entityManager.remove(findById(id));
        entityManager.getTransaction().commit();
    }

    @Override
    public void close() {
        entityManagerFactory.close();
    }
}
