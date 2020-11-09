package com.epam.java.training.lambda.task6;


import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainTest {

    private List<Integer> arrayListVasya = Arrays.asList(1, 31, 3);
    private List<Integer> arrayListAndrey = Arrays.asList(42, 38, 43);
    private List<Integer> arrayListAlex = Arrays.asList(10, 1, 98);
    private List<Student> arrayList = Arrays.asList(new Student("Vasya", 26, arrayListVasya, "Russian"),
            new Student("Andrey", 27, arrayListAndrey, "Russian"),
            new Student("Alex", 43, arrayListAlex, "English"),
            new Student("Alex", 45, arrayListAlex, "English"),
            new Student("Alex", 44, arrayListAlex, "English"),
            new Student("Alex", 25, arrayListAlex, "English"),
            new Student("Alex", 15, arrayListAlex, "English"),
            new Student("Alex", 25, arrayListAlex, "English"),
            new Student("Alex", 75, arrayListAlex, "English"),
            new Student("Alex", 42, arrayListAlex, "English"),
            new Student("Alex", 41, arrayListAlex, "English"),
            new Student("Alex", 27, arrayListAlex, "English"),
            new Student("Alex", 15, arrayListAlex, "English"));
    private List<Integer> expected = Arrays.asList(27, 69, 53, 55, 54, 35, 25, 35, 85, 52, 51, 37, 25);

    @Test
    public void mainConcurrent() {
        List<Integer> actual = arrayList.stream()
                .parallel().collect(new CustomCollector() {
                    @Override
                    public Set<Characteristics> characteristics() {
                        return Stream.of(Characteristics.CONCURRENT)
                                .collect(Collectors.toCollection(HashSet::new));
                    }
                });

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void mainConcurrentAndUnordered() {
        List<Integer> actual = arrayList.stream()
                .parallel().collect(new CustomCollector() {
                    @Override
                    public Set<Characteristics> characteristics() {
                        return Stream.of(Characteristics.CONCURRENT, Characteristics.UNORDERED)
                                .collect(Collectors.toCollection(HashSet::new));
                    }
                });

        Assert.assertThat(actual, IsIterableContainingInAnyOrder.containsInAnyOrder(27, 69, 53, 55, 54, 35, 25, 35, 85, 52, 51, 37, 25));
    }

    @Test
    public void mainConcurrentAndIdentityFinish() {
        List<Integer> actual = arrayList.stream()
                .parallel().collect(new CustomCollector() {
                    @Override
                    public Set<Characteristics> characteristics() {
                        return Stream.of(Characteristics.CONCURRENT, Characteristics.IDENTITY_FINISH)
                                .collect(Collectors.toCollection(HashSet::new));
                    }
                });

        Assert.assertEquals(arrayList, actual);
    }

    @Test
    public void mainUnordered() {
        List<Integer> actual = arrayList.stream()
                .parallel().collect(new CustomCollector() {
                    @Override
                    public Set<Characteristics> characteristics() {
                        return Stream.of(Characteristics.UNORDERED)
                                .collect(Collectors.toCollection(HashSet::new));
                    }
                });

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void mainUnorderedAndIdentityFinish() {
        List<Integer> actual = arrayList.stream()
                .parallel().collect(new CustomCollector() {
                    @Override
                    public Set<Characteristics> characteristics() {
                        return Stream.of(Characteristics.UNORDERED, Characteristics.IDENTITY_FINISH)
                                .collect(Collectors.toCollection(HashSet::new));
                    }
                });

        Assert.assertEquals(arrayList, actual);
    }

    @Test
    public void mainIdentityFinish() {
        List<Integer> actual = arrayList.stream()
                .parallel().collect(new CustomCollector() {
                    @Override
                    public Set<Characteristics> characteristics() {
                        return Stream.of(Characteristics.IDENTITY_FINISH)
                                .collect(Collectors.toCollection(HashSet::new));
                    }
                });

        Assert.assertEquals(arrayList, actual);
    }

    @Test
    public void mainAll() {
        List<Integer> actual = arrayList.stream()
                .parallel()
                .collect(new CustomCollector() {
                    @Override
                    public Set<Characteristics> characteristics() {
                        return Stream.of(Characteristics.IDENTITY_FINISH, Characteristics.UNORDERED, Characteristics.CONCURRENT)
                                .collect(Collectors.toCollection(HashSet::new));
                    }
                });

        Assert.assertThat(actual, IsIterableContainingInAnyOrder.containsInAnyOrder(
                new Student("Vasya", 26, arrayListVasya, "Russian"),
                new Student("Andrey", 27, arrayListAndrey, "Russian"),
                new Student("Alex", 43, arrayListAlex, "English"),
                new Student("Alex", 45, arrayListAlex, "English"),
                new Student("Alex", 44, arrayListAlex, "English"),
                new Student("Alex", 25, arrayListAlex, "English"),
                new Student("Alex", 15, arrayListAlex, "English"),
                new Student("Alex", 25, arrayListAlex, "English"),
                new Student("Alex", 75, arrayListAlex, "English"),
                new Student("Alex", 42, arrayListAlex, "English"),
                new Student("Alex", 41, arrayListAlex, "English"),
                new Student("Alex", 27, arrayListAlex, "English"),
                new Student("Alex", 15, arrayListAlex, "English")));
    }
}