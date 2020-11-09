package com.epam.java.training.webservices.task1.service.impl;


import com.epam.java.training.webservices.task1.model.User;
import com.epam.java.training.webservices.task1.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private static final Map<Long, User> userMap = new HashMap<>();

    @Override
    public Collection<User> getAll() {
        return userMap.values();
    }

    @Override
    public User getUserById(Long id) {
        return userMap.get(id);
    }

    @Override
    public User createUser(User user) {
        userMap.put(user.getId(), user);
        return userMap.get(user.getId());
    }

    @Override
    public User updateUser(User user) {
        User userFromMap = userMap.get(user.getId());
        if (Objects.isNull(userFromMap)) {
            throw new NullPointerException("User doesn't found with id:" + user.getId());
        }
        userFromMap.setFirstName(user.getFirstName());
        userFromMap.setLastName(user.getLastName());
        userFromMap.setEmail(user.getEmail());
        userFromMap.setLogin(user.getLogin());
        return userMap.put(userFromMap.getId(), userFromMap);
    }

    @Override
    public User deleteUser(Long id) {
        return userMap.remove(id);
    }

    @Override
    public byte[] uploadUserLogo(Long id, byte[] bytes) {
        User user = userMap.get(id);
        user.setImageFile(bytes);
        return userMap.get(id).getImageFile();
    }
}
