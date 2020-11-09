package com.epam.java.training.jvm.task1.model;

import java.util.Objects;

/**
 * Created by Vladislav Zhelezov.
 */
public class Cat {

    private String color;
    private String breed;
    private String gentle;
    private Integer price;
    private String master;

    public Cat(String color, String master) {
        this.color = color;
        this.master = master;
    }

    public Cat(String color, String breed, String gentle, Integer price, String master) {
        this.color = color;
        this.breed = breed;
        this.gentle = gentle;
        this.price = price;
        this.master = master;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getGentle() {
        return gentle;
    }

    public void setGentle(String gentle) {
        this.gentle = gentle;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getMaster() {
        return master;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, breed, gentle, price, master);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return Objects.equals(color, cat.color) &&
                Objects.equals(breed, cat.breed) &&
                Objects.equals(gentle, cat.gentle) &&
                Objects.equals(price, cat.price) &&
                Objects.equals(master, cat.master);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "color='" + color + '\'' +
                ", breed='" + breed + '\'' +
                ", gentle='" + gentle + '\'' +
                ", price=" + price +
                ", master='" + master + '\'' +
                '}';
    }
}
