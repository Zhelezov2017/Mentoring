package com.epam.java.training.lambda.task5.model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Author {

    private String name;
    private Short age;
    private List<Book> books;

    public Author(String name, Short age, List<Book> books) {
        this.name = name;
        this.age = age;
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        return Objects.equals(name, author.name) &&
                Objects.equals(age, author.age) &&
                Objects.equals(books, author.books);
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", books=" + books.stream().map(Book::getTitle).collect(Collectors.toList()) +
                '}';
    }
}
