package com.epam.java.training.lambda.task5;

import com.epam.java.training.lambda.task5.model.Author;
import com.epam.java.training.lambda.task5.model.Book;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static Book lordOfTheRings = new Book("Lord of the Rings", 1300);
    private static Book lastHero = new Book("Last hero", 500);
    private static Book loveAndDeath = new Book("Love and death", 1100);
    private static Book warAndPeace = new Book("War and Peace", 2000);
    private static Book theAwakening = new Book("The Awakening", 470);
    private static Book theKreutzerSonata = new Book("The Kreutzer Sonata", 140);


    private static Author tolkien = new Author("Tolkien", Short.parseShort("64"), Arrays.asList(
            lordOfTheRings,
            lastHero,
            loveAndDeath));
    private static Author tolstoy = new Author("Tolstoy", Short.parseShort("89"), Arrays.asList(
            warAndPeace,
            theAwakening,
            theKreutzerSonata));
    private static Author tigrov = new Author("Tigrov", Short.parseShort("34"), Arrays.asList(
            lordOfTheRings,
            theAwakening));

    public static void main(String[] args) {
        init();
        // 1 task
        Arrays.stream(books())
                .parallel()
                .filter(p -> p.getNumberOfPages() > 200)
                .forEach(System.out::println);
        System.out.println();

        // 2 task
        Arrays.stream(books())
                .parallel()
                .max(Comparator.comparing(Book::getNumberOfPages))
                .ifPresent(p -> System.out.println("Max: " + p.getNumberOfPages()));

        Arrays.stream(books())
                .parallel()
                .min(Comparator.comparing(Book::getNumberOfPages))
                .ifPresent(min -> System.out.println("Min: " + min.getNumberOfPages()));
        System.out.println();

        // 3 task
        Arrays.stream(books())
                .parallel()
                .filter(book -> {
                    List<Author> authors = book.getAuthors();
                    return authors.size() == 1;
                })
                .forEach(System.out::println);


        System.out.println();

        // 4 task
        Arrays.stream(books())
                .sorted(Comparator.comparing(Book::getNumberOfPages))
                .forEach(System.out::println);
        System.out.println();
        Arrays.stream(books())
                .sorted(Comparator.comparing(Book::getTitle))
                .forEach(System.out::println);
        System.out.println();

        // 5 and 6 task
        Arrays.stream(books()).parallel()
                .map(Book::getTitle)
                .collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println();

        // 7 task
        Arrays.stream(books()).parallel()
                .map(Book::getAuthors)
                .distinct()
                .forEach(System.out::println);
        System.out.println();

        // use Optional
        Arrays.stream(books())
                .parallel()
                .max(Comparator.comparing(Book::getNumberOfPages))
                .map(Book::getTitle)
                .ifPresent(p -> System.out.println("Title a max book: " + p));

        Arrays.stream(books())
                .map(Book::getAuthors)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    private static void init() {
        lordOfTheRings.setAuthors(Arrays.asList(tolkien, tigrov));
        lastHero.setAuthors(Collections.singletonList(tolkien));
        loveAndDeath.setAuthors(Collections.singletonList(tolkien));
        warAndPeace.setAuthors(Collections.singletonList(tolstoy));
        theAwakening.setAuthors(Arrays.asList(tolstoy, tigrov));
        theKreutzerSonata.setAuthors(Collections.singletonList(tolstoy));
    }

    private static Book[] books() {
        return new Book[]{
                lordOfTheRings,
                lastHero,
                loveAndDeath,
                warAndPeace,
                theAwakening,
                theKreutzerSonata
        };
    }


    private static Author[] authors() {
        return new Author[]{
                tolkien,
                tolstoy,
                tigrov
        };
    }
}
