package com.epam.java.training.hibernate.task1.service;


import java.util.List;

public interface MarketService<T> {
    void save(T object);

    List<T> findAll();

    T findById(Long id);

    T update(T object);

    void delete(Long id);

    void close();
}
