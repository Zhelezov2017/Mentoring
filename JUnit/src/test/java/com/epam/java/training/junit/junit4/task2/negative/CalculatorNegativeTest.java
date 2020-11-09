package com.epam.java.training.junit.junit4.task2.negative;

import com.epam.java.training.junit.junit4.task2.Calculator;
import org.junit.Test;

public class CalculatorNegativeTest {

    private Calculator calculator = new Calculator();

    //Double check
    @Test(expected = IllegalArgumentException.class)
    public void DoubleDivision_IllegalArgumentException_DivisionOfTwoNumbers() {
        calculator.divisionDouble(57.8, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void DoubleRoot_IllegalArgumentException_RootFromOneNumber() {
        calculator.rootDouble(-4.4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void DoublePower_IllegalArgumentException_PowerOneNumberToThePower() {
        calculator.powerDouble(0, 3);
    }

    //Integer check
    @Test(expected = IllegalArgumentException.class)
    public void IntegerDivision_IllegalArgumentException_DivisionOfTwoNumbers() {
        calculator.divisionInteger(56, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void IntegerRoot_IllegalArgumentException_RootFromOneNumber() {
        calculator.rootInteger(-10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void IntegerPower_IllegalArgumentException_PowerOneNumberToThePower() {
        calculator.powerInteger(0, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isPrime_IllegalArgumentException_DefineAPrime() {
        calculator.isPrime(-43214);
    }

    @Test(expected = IllegalArgumentException.class)
    public void FibonacciSequence_IllegalArgumentException_Return10Numbers() {
        calculator.fibonacciNumbers(-10);
    }
}
