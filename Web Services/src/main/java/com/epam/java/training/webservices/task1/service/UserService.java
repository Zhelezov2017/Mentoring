package com.epam.java.training.webservices.task1.service;


import com.epam.java.training.webservices.task1.model.User;

import java.util.Collection;

public interface UserService {

    Collection<User> getAll();

    User getUserById(Long id);

    User createUser(User user);

    User updateUser(User user);

    User deleteUser(Long id);

    byte[] uploadUserLogo(Long id, byte[] bytes);
}
