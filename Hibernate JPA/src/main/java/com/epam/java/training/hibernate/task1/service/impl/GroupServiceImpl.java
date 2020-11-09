package com.epam.java.training.hibernate.task1.service.impl;


import com.epam.java.training.hibernate.task1.data.entity.Group;
import com.epam.java.training.hibernate.task1.service.MarketService;

import javax.persistence.*;
import java.util.List;

import static com.epam.java.training.hibernate.task2.configuration.EntityManagerConfiguration.PERSISTENT_UNIT_NAME;

public class GroupServiceImpl implements MarketService<Group> {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
    @PersistenceContext
    private EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void save(Group group) {
        entityManager.getTransaction().begin();
        entityManager.persist(group);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Group> findAll() {
        entityManager.getTransaction().begin();
        Query groups = entityManager.createNativeQuery("Group_findAll");
        List resultList = groups.getResultList();
        entityManager.getTransaction().commit();
        return resultList;
    }

    @Override
    public Group findById(Long id) {
        entityManager.getTransaction().begin();
        Group group = entityManager.find(Group.class, id);
        entityManager.getTransaction().commit();
        return group;
    }

    @Override
    public Group update(Group group) {
        entityManager.getTransaction().begin();
        Group mergeGroup = entityManager.merge(group);
        entityManager.getTransaction().commit();
        return mergeGroup;
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
