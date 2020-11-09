package com.epam.java.training.hibernate.task1.service.impl;


import com.epam.java.training.hibernate.task1.data.entity.Friendship;
import com.epam.java.training.hibernate.task1.service.MarketService;

import javax.persistence.*;
import java.util.List;

import static com.epam.java.training.hibernate.task2.configuration.EntityManagerConfiguration.PERSISTENT_UNIT_NAME;

public class FriendshipServiceImpl implements MarketService<Friendship> {

    private static final String SELECT_LIST_FRIENDSHIPS = "SELECT friendships from FRIENDSHIPS friendships";

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
    @PersistenceContext
    private EntityManager entityManager = entityManagerFactory.createEntityManager();


    @Override
    public void save(Friendship friendship) {
        entityManager.getTransaction().begin();
        entityManager.persist(friendship);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Friendship> findAll() {
        entityManager.getTransaction().begin();
        List resultList = entityManager.createQuery(SELECT_LIST_FRIENDSHIPS).getResultList();
        entityManager.getTransaction().commit();
        return resultList;
    }

    @Override
    public Friendship findById(Long id) {
        entityManager.getTransaction().begin();
        Friendship friendship = entityManager.find(Friendship.class, id);
        entityManager.getTransaction().commit();
        return friendship;
    }

    @Override
    public Friendship update(Friendship friendship) {
        entityManager.getTransaction().begin();
        Friendship mergeFriendship = entityManager.merge(friendship);
        entityManager.getTransaction().commit();
        return mergeFriendship;
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
