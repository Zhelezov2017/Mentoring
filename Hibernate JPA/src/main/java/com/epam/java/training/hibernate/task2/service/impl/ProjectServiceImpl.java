package com.epam.java.training.hibernate.task2.service.impl;


import com.epam.java.training.hibernate.task2.data.entity.Project;
import com.epam.java.training.hibernate.task2.service.CrudService;

import static com.epam.java.training.hibernate.task2.configuration.EntityManagerConfiguration.entityManager;
import static com.epam.java.training.hibernate.task2.configuration.EntityManagerConfiguration.entityManagerFactory;

public class ProjectServiceImpl implements CrudService<Project> {



    @Override
    public Project create(Project project) {
        entityManager.getTransaction().begin();
        entityManager.persist(project);
        entityManager.getTransaction().commit();
        return project;
    }

    @Override
    public Project findById(Long id) {
        entityManager.getTransaction().begin();
        Project project = entityManager.find(Project.class, id);
        entityManager.getTransaction().commit();
        return project;
    }

    @Override
    public Project delete(Long id) {
        entityManager.getTransaction().begin();
        Project project = entityManager.find(Project.class, id);
        entityManager.remove(project);
        entityManager.getTransaction().begin();
        return project;
    }

    @Override
    public Project update(Long id) {
        entityManager.getTransaction().begin();
        Project project = entityManager.find(Project.class, id);
        Project mergeProject = entityManager.merge(project);
        entityManager.getTransaction().commit();
        return mergeProject;
    }

    @Override
    public void close() {
        entityManagerFactory.close();
    }
}
