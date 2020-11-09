package com.epam.java.training.nosql.task2.service;


import com.epam.java.training.nosql.task2.entity.SubTask;
import com.epam.java.training.nosql.task2.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();

    List<Task> overdueTasks();

    List<Task> getAllTasksWithCategory(String category);

    List<SubTask> getAllSubTasksByTasksWithCategory(String category);

    List<String> getDescriptionsByWord(String word);

    List<String> getNamesBySubTaskName(String name);

    void insertTask(Task task);

    void updateTask(Task task);

    void deleteTask(String name);

    void insertSubTask(SubTask subTask);

    void deleteSubTask(SubTask subTask);

    void updateAllSubTasksInTask(String name, List<SubTask> subTasks);

    void deleteAllSubTasksInTask(String name);
}
