package com.epam.java.training.nosql.task2.service.impl;


import com.epam.java.training.nosql.task2.entity.SubTask;
import com.epam.java.training.nosql.task2.entity.Task;
import com.epam.java.training.nosql.task2.service.TaskService;
import com.mongodb.MongoClient;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;
import dev.morphia.query.internal.MorphiaCursor;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TaskServiceImpl implements TaskService {

    public static final String HOST = "host";
    public static final String PORT = "port";
    public static final String DB_NAME = "databaseName";
    //need to change on own
    private static final String PATH = "C:\\Users\\Vladislav_Zhelezov\\EPAM\\Mentoring\\Java\\vladislav-zhelezov" +
            "\\NoSql\\";
    private Datastore datastore;
    private Morphia morphia;

    public TaskServiceImpl() {
        Properties propertyFile = getPropertiesFile();
        morphia = new Morphia();
        this.datastore = morphia.createDatastore(new MongoClient(propertyFile.getProperty(HOST),
                Integer.parseInt(propertyFile.getProperty(PORT))), propertyFile.getProperty(DB_NAME));
        datastore.ensureIndexes();
    }

    @Override
    public List<Task> getAllTasks() {
        Query<Task> tasks = datastore.find(Task.class);
        MorphiaCursor<Task> taskMorphiaCursor = tasks.find();
        return taskMorphiaCursor.toList();
    }

    @Override
    public List<Task> overdueTasks() {
        return datastore.find(Task.class).field("deadline")
                .lessThan(LocalDate.now())
                .find()
                .toList();
    }

    @Override
    public List<Task> getAllTasksWithCategory(String category) {
        return datastore.find(Task.class).field("category").equal(category)
                .find()
                .toList();
    }

    @Override
    public List<SubTask> getAllSubTasksByTasksWithCategory(String category) {
        return datastore.find(Task.class).field("category").equal(category)
                .find()
                .toList().stream()
                .map(Task::getSubTasks)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getDescriptionsByWord(String word) {
        return datastore.find(Task.class).filter("description",
                Pattern.compile(word, Pattern.CASE_INSENSITIVE)).asList().stream()
                .map(Task::getDescription)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getNamesBySubTaskName(String name) {
        return datastore.find(SubTask.class).filter("name",
                Pattern.compile(name, Pattern.CASE_INSENSITIVE)).asList().stream()
                .map(SubTask::getName)
                .collect(Collectors.toList());
    }

    @Override
    public void insertTask(Task task) {
        morphia.map(Task.class);
        datastore.save(task);
    }

    @Override
    public void updateTask(Task task) {
        morphia.map(Task.class);
        Query<Task> queryTask = datastore.createQuery(Task.class).field("name").equal(task.getName());
        UpdateOperations<Task> set = datastore.createUpdateOperations(Task.class)
                .set("id", task.getId())
                .set("dateOfCreation", task.getDateOfCreation())
                .set("deadline", task.getDeadline())
                .set("name", task.getName())
                .set("description", task.getDescription())
                .set("subTasks", task.getSubTasks())
                .set("category", task.getCategory());

        datastore.update(queryTask, set);
    }

    @Override
    public void deleteTask(String name) {
        morphia.map(Task.class);
        Task task = datastore.createQuery(Task.class).field("name").equal(name).first();
        datastore.delete(task);
    }

    @Override
    public void insertSubTask(SubTask subTask) {
        morphia.map(SubTask.class);
        datastore.save(subTask);
        datastore.save(subTask);
    }

    @Override
    public void deleteSubTask(SubTask subTask) {
        morphia.map(SubTask.class);
        SubTask task = datastore.createQuery(SubTask.class).field("id").equal(subTask.getId()).first();
        datastore.delete(task);
    }

    @Override
    public void updateAllSubTasksInTask(String name, List<SubTask> subTasks) {
        Query<Task> queryTask = datastore.createQuery(Task.class).field("name").equal(name);
        UpdateOperations<Task> set = datastore.createUpdateOperations(Task.class).set("subTasks", subTasks);
        datastore.update(queryTask, set);
    }

    @Override
    public void deleteAllSubTasksInTask(String name) {
        Query<Task> queryTask = datastore.createQuery(Task.class).field("name").equal(name);
        UpdateOperations<Task> set = datastore.createUpdateOperations(Task.class).set("subTasks", new ArrayList<>());
        datastore.update(queryTask, set);
    }

    private Properties getPropertiesFile() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(PATH + "src\\main\\resources\\dbsettings.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
