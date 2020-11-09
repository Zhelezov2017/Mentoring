package com.epam.java.training.nosql.task2;


import com.epam.java.training.nosql.task2.entity.SubTask;
import com.epam.java.training.nosql.task2.entity.Task;
import com.epam.java.training.nosql.task2.service.TaskService;
import com.epam.java.training.nosql.task2.service.impl.TaskServiceImpl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ConsoleApplication {

    public void run(String[] args) {
        TaskService taskService = new TaskServiceImpl();
        SubTask subTask1 = new SubTask(1L, "Full name", "subTask 1");
        SubTask subTask2 = new SubTask(2L, "Short name", "subTask 2");
        Task task1 = new Task(1L, LocalDate.of(2020, 2, 7),
                LocalDate.of(2020, 3, 20), "Main task", "Description", "MAIN");
        List<SubTask> listSubTask = Arrays.asList(subTask1, subTask2);
        Task task2 = new Task(2L, LocalDate.of(2020, 2, 7),
                LocalDate.of(2020, 8, 20),
                "Main task for subTask", "Description for subtask", listSubTask, "TRY");
        taskService.insertSubTask(subTask1);
        taskService.insertSubTask(subTask2);
        taskService.insertTask(task1);
        taskService.insertTask(task1);
        taskService.insertTask(task2);
        taskService.getAllTasks();
        taskService.getAllSubTasksByTasksWithCategory("MAIN");
        taskService.getAllTasksWithCategory("MAIN");
    }
}
