package com.epam.java.training.hibernate.task1.service.impl;


import com.epam.java.training.hibernate.task1.data.entity.User;
import com.epam.java.training.hibernate.task1.data.entity.User_;
import com.epam.java.training.hibernate.task1.service.MarketService;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

import static com.epam.java.training.hibernate.task2.configuration.EntityManagerConfiguration.PERSISTENT_UNIT_NAME;

public class UserServiceImpl implements MarketService<User> {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
    @PersistenceContext
    private EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void save(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<User> findAll() {
        entityManager.getTransaction().begin();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> rootEntry = cq.from(User.class);
        CriteriaQuery<User> all = cq.select(rootEntry);
        TypedQuery<User> allQuery = entityManager.createQuery(all);
        entityManager.getTransaction().commit();
        return allQuery.getResultList();
    }

    @Override
    public User findById(Long id) {
        entityManager.getTransaction().begin();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> rootEntry = cq.from(User.class);
        Predicate equal = cb.equal(rootEntry.get(User_.ID), id);
        cq.where(equal);
        User user = entityManager.createQuery(cq).getSingleResult();
        entityManager.getTransaction().commit();
        return user;
    }

    @Override
    public User update(User user) {
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
        return user;
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
