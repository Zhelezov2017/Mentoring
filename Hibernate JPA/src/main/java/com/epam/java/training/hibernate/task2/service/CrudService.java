package com.epam.java.training.hibernate.task2.service;


public interface CrudService<T> {
    T create(T entity);

    T findById(Long id);

    T delete(Long id);

    T update(Long id);

    void close();
}
