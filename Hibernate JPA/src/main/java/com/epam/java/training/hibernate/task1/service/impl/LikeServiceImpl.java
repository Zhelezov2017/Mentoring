package com.epam.java.training.hibernate.task1.service.impl;


import com.epam.java.training.hibernate.task1.data.entity.Like;
import com.epam.java.training.hibernate.task1.service.MarketService;

import javax.persistence.*;
import java.util.List;

import static com.epam.java.training.hibernate.task2.configuration.EntityManagerConfiguration.PERSISTENT_UNIT_NAME;

public class LikeServiceImpl implements MarketService<Like> {

    private static final String SELECT_LIST_LIKES = "SELECT likes from Likes likes";

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
    @PersistenceContext
    private EntityManager entityManager = entityManagerFactory.createEntityManager();


    @Override
    public void save(Like like) {
        entityManager.getTransaction().begin();
        entityManager.persist(like);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Like> findAll() {
        entityManager.getTransaction().begin();
        List likes = entityManager.createQuery(SELECT_LIST_LIKES).getResultList();
        entityManager.getTransaction().commit();
        return likes;
    }

    @Override
    public Like findById(Long id) {
        entityManager.getTransaction().begin();
        Like like = entityManager.find(Like.class, id);
        return like;
    }

    @Override
    public Like update(Like like) {
        entityManager.getTransaction().begin();
        Like mergeLike = entityManager.merge(like);
        entityManager.getTransaction().commit();
        return mergeLike;
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
