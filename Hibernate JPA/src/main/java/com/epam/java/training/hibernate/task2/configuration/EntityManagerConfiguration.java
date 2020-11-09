package com.epam.java.training.hibernate.task2.configuration;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

public class EntityManagerConfiguration {
    public static final String PERSISTENT_UNIT_NAME = "xe";

    @PersistenceUnit
    public static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
    @PersistenceContext
    public static EntityManager entityManager = entityManagerFactory.createEntityManager();

}
