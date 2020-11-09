package com.epam.java.training.lambda.task4;

@FunctionalInterface
public interface ThirdFunction<T, U, M, R> {

    /**
     * Applies this function to the given arguments.
     *
     * @param t the first function argument
     * @param u the second function argument
     * @param m the third function argument
     * @return the function result
     */
    R apply(T t, U u, M m);
}
