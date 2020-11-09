package com.epam.java.training.junit.junit4.task6.negative;

import com.epam.java.training.junit.junit4.task2.Calculator;
import org.testng.annotations.*;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;


public class CalculatorNegativeTest {


    private Calculator calculator = new Calculator();

    //Double check
    @Test(expectedExceptions = NumberFormatException.class, priority = 1, groups = "Double Value")
    public void testDoubleAddition_ParseString_AdditionOfTwoNumbers() {
        Double count = calculator.additionDouble(Integer.parseInt("no value") + 4.5, 4.1);
        assertEquals(count, 8.6, 0.1);
    }

    @Test(expectedExceptions = NumberFormatException.class, priority = 1, groups = "Double Value")
    public void testDoubleSubtraction_ParseString_SubtractionOfOneFromTheOther() {
        Double count = calculator.subtractionDouble(Integer.parseInt("no value"), 11.3);
        assertEquals(count, -11.3, 0.0001);
    }

    @Test(expectedExceptions = NumberFormatException.class, priority = 1, groups = "Double Value")
    public void testDoubleMultiplication_ParseString_MultiplicationOfOneOnTheOther() {
        Double count = calculator.multiplicationDouble(Integer.parseInt("no value"), 12.2);
        assertEquals(count, 0, 0.0001);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, groups = "Double Value")
    public void testDoubleDivision_IllegalArgumentException_DivisionOfTwoNumbers() {
        Double count = calculator.divisionDouble(57.8, 0);
        assertEquals(count, 57.8, 0.0001);
    }

    @Test(dependsOnMethods = "testIntegerDivision_IllegalArgumentException_DivisionOfTwoNumbers", expectedExceptions = IllegalArgumentException.class, groups = "Double Value")
    @Parameters({"actual", "delta"})
    public void testDoubleRoot_IllegalArgumentException_RootFromOneNumber(@Optional(value = "-2") String actual, @Optional(value = "0.1") String delta) {
        Double count = calculator.rootDouble(-4.4);
        assertEquals(count, Double.parseDouble(actual), Double.parseDouble(delta));
    }

    @Test(expectedExceptions = IllegalArgumentException.class, groups = "Double Value")
    @Parameters({"actual", "delta"})
    public void testDoublePower_IllegalArgumentException_PowerOneNumberToThePower(@Optional(value = "3") int actual, @Optional(value = "0.001") double delta) {
        Double count = calculator.powerDouble(0, 3);
        assertEquals(count, actual, delta);
    }

    //Integer check
    @Test(expectedExceptions = NumberFormatException.class, groups = "Integer Value")
    public void testIntegerAddition_ParseString_AdditionOfTwoNumbers() {
        Integer count = calculator.additionInteger(Integer.parseInt("no value"), 4);
        assertEquals(count, 4, 0.0001);
    }

    @Test(expectedExceptions = NumberFormatException.class, groups = "Integer Value")
    public void testIntegerSubtraction_ParseString_SubtractionOfOneFromTheOther() {
        Integer count = calculator.subtractionInteger(Integer.parseInt("no value"), 11);
        assertEquals(count, 11, 0.0001);
    }

    @Test(dependsOnMethods = "testIntegerDivision_IllegalArgumentException_DivisionOfTwoNumbers", expectedExceptions = IllegalArgumentException.class, groups = "Integer Value")
    public void testIntegerMultiplication_ParseString_MultiplicationOfOneOnTheOther() {
        Integer count = calculator.multiplicationInteger(Integer.parseInt("no value"), 12);
        assertEquals(count, 0, 0.0001);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, priority = 2, groups = "Integer Value")
    public void testIntegerDivision_IllegalArgumentException_DivisionOfTwoNumbers() {
        Integer count = calculator.divisionInteger(56, 0);
        assertEquals(count, 8, 0.0001);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, priority = 2, groups = "Integer Value")
    public void testIntegerRoot_IllegalArgumentException_RootFromOneNumber() {
        Integer count = calculator.rootInteger(-10);
        assertEquals(count, -5, 0.0001);
    }

    @Test(dependsOnMethods = "testIntegerRoot_IllegalArgumentException_RootFromOneNumber", expectedExceptions = IllegalArgumentException.class, groups = "Integer Value")
    public void testIntegerPower_IllegalArgumentException_PowerOneNumberToThePower() {
        Integer count = calculator.powerInteger(0, 3);
        assertEquals(count, 0, 0.0001);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, groups = "Integer Value")
    public void testIsPrime_IllegalArgumentException_DefineAPrime() {
        boolean isPrime = calculator.isPrime(-43214);
        assertTrue(isPrime);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, priority = 3, groups = "Integer Value")
    @Parameters({"valueSide"})
    public void testFibonacciSequence_IllegalArgumentException_Return10Numbers(@Optional(value = "-10") String valueSide) {
        List<Integer> listFibonacciActual = Arrays.asList(0, 1, 1, 2, 3, 5, 8, 13, 21, 34);
        List listFibonacciExpected = calculator.fibonacciNumbers(Integer.parseInt(valueSide));
        assertThat(listFibonacciActual, equalTo(listFibonacciExpected));
    }

    @Test(dependsOnMethods = "testIsPrime_IllegalArgumentException_DefineAPrime", expectedExceptions = IllegalArgumentException.class, priority = 4)
    public void testApproximatelyPi_IllegalArgumentException_GiveRowForPiAndCountValueAdditionally() {
        Double pi = calculator.approximatelyPi(1000000000);
        assertEquals(pi, 3.14159, 0.0001);
    }
}
