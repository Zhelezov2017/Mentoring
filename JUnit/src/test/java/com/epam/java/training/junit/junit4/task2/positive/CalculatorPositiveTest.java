package com.epam.java.training.junit.junit4.task2.positive;

import com.epam.java.training.junit.junit4.task2.Calculator;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Vladislav Zhelezov.
 */
public class CalculatorPositiveTest {

    private Calculator calculator;


    @Before
    public void BeforeCalculator() {
        calculator = new Calculator();
    }


    @After
    public void AfterCalculator() {
        calculator = null;
    }

    //Double check
    @Test
    public void DoubleAddition_7_AdditionOfTwoNumbers() {
        Double count = calculator.additionDouble(3.3, 4.1);
        assertEquals(count, 7.4, 0.0001);
    }

    @Test
    public void DoubleSubtraction_46_SubtractionOfOneFromTheOther() {
        Double count = calculator.subtractionDouble(57.5, 11.3);
        assertEquals(count, 46.2, 0.0001);
    }

    @Test
    public void DoubleMultiplication_48_MultiplicationOfOneOnTheOther() {
        Double count = calculator.multiplicationDouble(4, 12.2);
        assertEquals(count, 48.8, 0.0001);
    }

    @Test
    public void DoubleDivision_8_DivisionOfTwoNumbers() {
        Double count = calculator.divisionDouble(57.8, 0.2);
        assertEquals(count, 289, 0.0001);
    }

    @Test
    public void DoubleRoot_8_RootFromOneNumber() {
        Double count = calculator.rootDouble(100.8);
        assertEquals(count, 10, 0.1);
    }

    @Test
    @Ignore
    public void DoubleRootIgnore_10_RootFromOneNumber() {
        Double count = calculator.rootDouble(100);
        assertEquals(count, 10, 0.1);
    }

    @Test
    public void DoublePower_100_PowerOneNumberToThePower() {
        Double count = calculator.powerDouble(10.5, 3);
        assertEquals(count, 1157.625, 0.0001);
    }

    //Integer check
    @Test
    public void IntegerAddition_7_AdditionOfTwoNumbers() {
        Integer count = calculator.additionInteger(3, 4);
        assertEquals(count, 7, 0.0001);
    }

    @Test
    public void IntegerSubtraction_46_SubtractionOfOneFromTheOther() {
        Integer count = calculator.subtractionInteger(57, 11);
        assertEquals(count, 46, 0.0001);
    }

    @Test
    public void IntegerMultiplication_48_MultiplicationOfOneOnTheOther() {
        Integer count = calculator.multiplicationInteger(4, 12);
        assertEquals(count, 48, 0.0001);
    }

    @Test
    public void IntegerDivision_8_DivisionOfTwoNumbers() {
        Integer count = calculator.divisionInteger(56, 7);
        assertEquals(count, 8, 0.0001);
    }

    @Test
    public void IntegerRoot_8_RootFromOneNumber() {
        Integer count = calculator.rootInteger(100);
        assertEquals(count, 10, 0.0001);
    }

    @Test
    public void IntegerPower_100_PowerOneNumberToThePower() {
        Integer count = calculator.powerInteger(10, 3);
        assertEquals(count, 1000, 0.0001);
    }

    @Test
    public void isPrime_11_DefineAPrime() {
        boolean isPrime = calculator.isPrime(11);
        assertTrue(isPrime);
    }

    @Test
    public void FibonacciSequence_10Numbers_Return10Numbers() {
        List<Integer> listFibonacciActual = Arrays.asList(0, 1, 1, 2, 3, 5, 8, 13, 21, 34);
        List listFibonacciExpected = calculator.fibonacciNumbers(10);
        assertThat(listFibonacciActual, equalTo(listFibonacciExpected));
    }
}