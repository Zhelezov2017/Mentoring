package com.epam.java.training.spring_boot.task1.model;


public class Skill {

    private String  name;

    //Plus in salary
    private Integer rating;

    public Skill(String name, Integer rating) {
        this.name = name;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
