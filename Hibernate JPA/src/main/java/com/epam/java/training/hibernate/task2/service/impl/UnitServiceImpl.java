package com.epam.java.training.hibernate.task2.service.impl;


import com.epam.java.training.hibernate.task2.data.entity.Unit;
import com.epam.java.training.hibernate.task2.service.CrudService;

import static com.epam.java.training.hibernate.task2.configuration.EntityManagerConfiguration.entityManager;
import static com.epam.java.training.hibernate.task2.configuration.EntityManagerConfiguration.entityManagerFactory;

public class UnitServiceImpl implements CrudService<Unit> {

    @Override
    public Unit create(Unit unit) {
        entityManager.getTransaction().begin();
        entityManager.persist(unit);
        entityManager.getTransaction().commit();
        return unit;
    }

    @Override
    public Unit findById(Long id) {
        entityManager.getTransaction().begin();
        Unit unit = entityManager.find(Unit.class, id);
        entityManager.getTransaction().commit();
        return unit;
    }

    @Override
    public Unit delete(Long id) {
        entityManager.getTransaction().begin();
        Unit unit = entityManager.find(Unit.class, id);
        entityManager.remove(unit);
        entityManager.getTransaction().commit();
        return unit;
    }

    @Override
    public Unit update(Long id) {
        entityManager.getTransaction().begin();
        Unit user = entityManager.find(Unit.class, id);
        Unit mergeUnit = entityManager.merge(user);
        entityManager.getTransaction().commit();
        return mergeUnit;
    }

    @Override
    public void close() {
        entityManagerFactory.close();
    }
}
