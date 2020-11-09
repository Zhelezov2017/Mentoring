package com.epam.java.training.junit.junit4.task2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Calculator {

    public Double additionDouble(double value1, double value2) {
        return value1 + value2;
    }

    public Double subtractionDouble(double value1, double value2) {
        return value1 - value2;
    }

    public Double multiplicationDouble(double value1, double value2) {
        return value1 * value2;
    }

    public Double divisionDouble(double value1, double value2) {
        if (value2 == 0) throw new IllegalArgumentException();
        return value1 / value2;
    }

    public Double rootDouble(double value) {
        if (value < 0) throw new IllegalArgumentException();
        return Math.sqrt(value);
    }

    public Double powerDouble(double value1, double value2) {
        if (value1 <= 0) throw new IllegalArgumentException();
        return Math.pow(value1, value2);
    }

    public Integer additionInteger(int value1, int value2) {
        return value1 + value2;
    }

    public Integer subtractionInteger(int value1, int value2) {
        return value1 - value2;
    }

    public Integer multiplicationInteger(int value1, int value2) {
        return value1 * value2;
    }

    public Integer divisionInteger(int value1, int value2) {
        if (value2 == 0) throw new IllegalArgumentException();
        return value1 / value2;
    }

    public Integer rootInteger(int value) {
        if (value < 0) throw new IllegalArgumentException();
        return (int) Math.sqrt(value);
    }

    public Integer powerInteger(int value1, int value2) {
        if (value1 <= 0) throw new IllegalArgumentException();
        return (int) Math.pow(value1, value2);
    }

    public boolean isPrime(double value) {
        if (value == 0) return false;
        if (value == 1) return false;
        if (value < 0) throw new IllegalArgumentException();
        int count = 0;
        for (int i = 1; i <= value; i++) {
            if (value % i == 0) count++;
        }
        return count == 2;
    }

    public List<Integer> fibonacciNumbers(int value) {
        if (value <= 0) throw new IllegalArgumentException();
        if (value == 1) return Collections.singletonList(0);
        if (value == 2) return Arrays.asList(0, 1);
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        //value-2 because 2 value add before start iterated
        for (int i = 0; i < value - 2; i++) {
            int after = list.get(i);
            int before = list.get(i + 1);
            list.add(after + before);
        }
        return list;
    }

    public Double approximatelyPi(int i) {
        if (i > 1000000) throw new IllegalArgumentException();
        double count = 0;
        for (int j = 1; j <= i; j++) {
            count = count + 1 / (Math.pow(j, 2));
        }
        count = Math.sqrt(6 * count);
        return count;
    }
}
