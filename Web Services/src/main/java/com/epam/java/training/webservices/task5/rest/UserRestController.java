package com.epam.java.training.webservices.task5.rest;


import com.epam.java.training.webservices.task5.model.Task;
import com.epam.java.training.webservices.task5.model.User;
import com.epam.java.training.webservices.task5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Collection<User>> getAll() {
        try {
            Collection<User> users = new ArrayList<>(userService.getAll());
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.createUser(user);
        if (Objects.nonNull(newUser)) {
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        if (Objects.nonNull(user)) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        if (Objects.nonNull(user)) {
            return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        User user = userService.deleteUser(id);
        if (Objects.nonNull(user)) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/{userId}/tasks")
    public ResponseEntity<List<Task>> getTasksByUserId(@PathVariable("userId") Long userId) {
        try {
            List<Task> tasks = new ArrayList<>(userService.getTasksByUserId(userId));
            if (tasks.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{userId}/tasks")
    public ResponseEntity<Task> createTaskForUser(@PathVariable("userId") Long userId,
                                                  @RequestBody Task task) {
        Task newTask = userService.createTaskForUser(userId, task);
        if (Objects.nonNull(newTask)) {
            return new ResponseEntity<>(newTask, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/{userId}/tasks/{taskId}")
    public ResponseEntity<Task> getTaskByUserIdAndTaskId(@PathVariable("userId") Long userId,
                                                         @PathVariable("taskId") Long taskId) {
        Task task = userService.getTaskByUserIdAndTaskId(userId, taskId);
        if (Objects.nonNull(task)) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{userId}/tasks")
    public ResponseEntity<Task> updateTaskForUser(@PathVariable("userId") Long userId,
                                                  @RequestBody Task task) {
        if (Objects.nonNull(task)) {
            return new ResponseEntity<>(userService.updateTaskForUser(userId, task), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}/tasks/{taskId}")
    public ResponseEntity<Task> deleteTaskForUser(@PathVariable("userId") Long userId,
                                           @PathVariable("taskId") Long taskId) {
        Task task = userService.deleteTaskForUser(userId, taskId);
        if (Objects.nonNull(task)) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }
}
