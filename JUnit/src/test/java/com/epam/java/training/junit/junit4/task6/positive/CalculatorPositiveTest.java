package com.epam.java.training.junit.junit4.task6.positive;


import com.epam.java.training.junit.junit4.task2.Calculator;
import org.testng.annotations.*;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;



public class CalculatorPositiveTest {

    private Calculator calculator = new Calculator();

    //Double check
    @Test(priority = 1, groups = "Double Value")
    @Parameters({"actual", "delta"})
    public void testDoubleAddition_7_AdditionOfTwoNumbers(@Optional(value = "7.4") String actual, @Optional(value = "0.0001") String delta) {
        Double count = calculator.additionDouble(3.3, 4.1);
        assertEquals(count, Double.parseDouble(actual), Double.parseDouble(delta));
    }

    @Test(priority = 1, groups = "Double Value")
    @Parameters({"actual", "delta"})
    public void testDoubleSubtraction_46_SubtractionOfOneFromTheOther(@Optional(value = "46.2") String actual, @Optional(value = "0.0001") String delta) {
        Double count = calculator.subtractionDouble(57.5, 11.3);
        assertEquals(count, Double.parseDouble(actual), Double.parseDouble(delta));
    }

    @Test(priority = 2, groups = "Double Value")
    public void testDoubleMultiplication_48_MultiplicationOfOneOnTheOther() {
        Double count = calculator.multiplicationDouble(4, 12.2);
        assertEquals(count, 48.8, 0.0001);
    }

    @Test(priority = 2, groups = "Double Value")
    public void testDoubleDivision_8_DivisionOfTwoNumbers() {
        Double count = calculator.divisionDouble(57.8, 0.2);
        assertEquals(count, 289, 0.0001);
    }

    @Test(priority = 2, groups = "Double Value")
    public void testDoubleRoot_8_RootFromOneNumber() {
        Double count = calculator.rootDouble(100.8);
        assertEquals(count, 10, 0.1);
    }

    @Test(priority = 3, groups = "Double Value")
    @Ignore
    public void testDoubleRootIgnore_10_RootFromOneNumber() {
        Double count = calculator.rootDouble(100);
        assertEquals(count, 10, 0.1);
    }

    @Test(dependsOnMethods = "testDoubleDivision_8_DivisionOfTwoNumbers", priority = 3, groups = "Double Value")
    public void testDoublePower_100_PowerOneNumberToThePower() {
        Double count = calculator.powerDouble(10.5, 3);
        assertEquals(count, 1157.625, 0.0001);
    }

    //Integer check
    @Test(groups = "Integer Value")
    public void testIntegerAddition_7_AdditionOfTwoNumbers() {
        Integer count = calculator.additionInteger(3, 4);
        assertEquals(count, 7, 0.0001);
    }

    @Test(groups = "Integer Value")
    public void testIntegerSubtraction_46_SubtractionOfOneFromTheOther() {
        Integer count = calculator.subtractionInteger(57, 11);
        assertEquals(count, 46, 0.0001);
    }

    @Test(groups = "Integer Value")
    public void testIntegerMultiplication_48_MultiplicationOfOneOnTheOther() {
        Integer count = calculator.multiplicationInteger(4, 12);
        assertEquals(count, 48, 0.0001);
    }

    @Test(groups = "Integer Value")
    public void testIntegerDivision_8_DivisionOfTwoNumbers() {
        Integer count = calculator.divisionInteger(56, 7);
        assertEquals(count, 8, 0.0001);
    }

    @Test(groups = "Integer Value")
    public void testIntegerRoot_10_RootFromOneNumber() {
        Integer count = calculator.rootInteger(100);
        assertEquals(count, 10, 0.0001);
    }

    @Test(dependsOnMethods = "testIntegerMultiplication_48_MultiplicationOfOneOnTheOther", alwaysRun = true, groups = "Integer Value")
    public void testIntegerPower_100_PowerOneNumberToThePower() {
        Integer count = calculator.powerInteger(10, 3);
        assertEquals(count, 1000, 0.0001);
    }

    @Test(groups = "Integer Value")
    public void testIsPrime_11_DefineAPrime() {
        boolean isPrime = calculator.isPrime(11);
        assertTrue(isPrime);
    }

    @Test(groups = "Integer Value")
    public void testFibonacciSequence_10_Numbers_Return10Numbers() {
        List<Integer> listFibonacciActual = Arrays.asList(0, 1, 1, 2, 3, 5, 8, 13, 21, 34);
        List listFibonacciExpected = calculator.fibonacciNumbers(10);
        assertThat(listFibonacciActual, equalTo(listFibonacciExpected));
    }

    @Test(timeOut = 15, alwaysRun = true, priority = 3)
    @Parameters({"actual", "delta"})
    public void testApproximatelyPi_3_GiveRowForPiAndCountValueAdditionally(@Optional(value = "3.14159") String actual, @Optional(value = "0.0001") String delta) {
        Double pi = calculator.approximatelyPi(10000);
        assertEquals(pi, Double.parseDouble(actual), Double.parseDouble(delta));
    }

//    @Test
//    public void testCosineTaylorSeries() {
//        Double cos = this.calculator.approximatelyCos(1, 1000);
//        assertEquals(cos, Math.sqrt(2)/2, 0.1);
//    }
}