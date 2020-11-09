package com.epam.java.training.webservices.task5.service.impl;

import com.epam.java.training.webservices.task5.model.Task;
import com.epam.java.training.webservices.task5.model.User;
import com.epam.java.training.webservices.task5.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.WebService;
import java.util.*;
import java.util.stream.Collectors;

@Service
@WebService(name="UserService", serviceName="UserService" )
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
    @Transactional
    public User updateUser(User user) {
        User userFromMap = userMap.get(user.getId());
        if (Objects.isNull(userFromMap)) {
            throw new NullPointerException("User doesn't found with id:" + user.getId());
        }
        userFromMap.setName(user.getName());
        userFromMap.setSurname(user.getSurname());
        userFromMap.setMail(user.getMail());
        userFromMap.setTasks(user.getTasks());
        return userMap.put(userFromMap.getId(), userFromMap);
    }

    @Override
    public User deleteUser(Long id) {
        return userMap.remove(id);
    }

    @Override
    public List<Task> getTasksByUserId(Long userId) {
        return Optional.ofNullable(userMap.get(userId))
                .map(User::getTasks)
                .orElse(new ArrayList<>());
    }

    @Override
    @Transactional
    public Task getTaskByUserIdAndTaskId(Long userId, Long taskId) {
        return userMap.get(userId).getTasks().stream()
                .filter(task -> task.getId().equals(taskId))
                .findFirst()
                .orElseThrow();
    }

    @Override
    @Transactional
    public Task createTaskForUser(Long userId, Task task) {
        List<Task> tasks = userMap.get(userId).getTasks();
        tasks.add(task);
        userMap.get(userId).setTasks(tasks);
        User user = userMap.get(userId);
        userMap.put(user.getId(), user);
        return task;
    }

    @Override
    @Transactional
    public Task updateTaskForUser(Long userId, Task task) {
        Task taskFromList = getTaskByUserIdAndTaskId(userId, task.getId());
        taskFromList.setName(task.getName());
        taskFromList.setDescription(task.getDescription());
        taskFromList.setCreationDate(task.getCreationDate());
        taskFromList.setDeadLine(task.getDeadLine());
        List<Task> tasks = userMap.get(userId).getTasks();
        userMap.get(userId).setTasks(tasks);
        User user = userMap.get(userId);
        userMap.put(user.getId(), user);
        return task;
    }

    @Override
    @Transactional
    public Task deleteTaskForUser(Long userId, Long taskId) {
        Task taskByUserIdAndTaskId = getTaskByUserIdAndTaskId(userId, taskId);
        List<Task> tasks = userMap.get(userId).getTasks().stream()
                .filter(task -> !task.getId().equals(taskId))
                .collect(Collectors.toList());
        User user = userMap.get(userId);
        user.setTasks(tasks);
        userMap.put(user.getId(), user);
        return taskByUserIdAndTaskId;
    }
}
