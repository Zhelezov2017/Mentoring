package com.epam.java.training.lambda.task6;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CustomCollector implements Collector<Student, List<Student>, List<Integer>> {

    @Override
    public Supplier<List<Student>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<Student>, Student> accumulator() {
        return List::add;
    }

    @Override
    public BinaryOperator<List<Student>> combiner() {
        return (m, p) -> {
            m.addAll(p);
            return m;
        };
    }

    @Override
    public Function<List<Student>, List<Integer>> finisher() {
        return students -> students.stream()
                .map(e -> e.calculateLoverInNative(e.getAge()))
                .collect(Collectors.toList());
    }

    @Override
    public Set<Characteristics> characteristics() {
        return new HashSet<>();
    }
}
