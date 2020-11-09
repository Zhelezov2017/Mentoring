package com.epam.java.training.webservices.task5.service;


import com.epam.java.training.webservices.task5.model.Task;
import com.epam.java.training.webservices.task5.model.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Collection;
import java.util.List;

@WebService
public interface UserService {
    @WebMethod
    Collection<User> getAll();
    @WebMethod
    User getUserById(Long id);
    @WebMethod
    User createUser(User user);
    @WebMethod
    User updateUser(User user);
    @WebMethod
    User deleteUser(Long id);
    @WebMethod
    List<Task> getTasksByUserId(Long userId);
    @WebMethod
    Task getTaskByUserIdAndTaskId(Long userId, Long taskId);
    @WebMethod
    Task createTaskForUser(Long userId, Task task);
    @WebMethod
    Task updateTaskForUser(Long userId, Task task);
    @WebMethod
    Task deleteTaskForUser(Long userId, Long taskId);
}
