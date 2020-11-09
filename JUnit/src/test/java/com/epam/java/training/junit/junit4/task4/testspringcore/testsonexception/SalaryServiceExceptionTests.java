package com.epam.java.training.junit.junit4.task4.testspringcore.testsonexception;

import com.epam.java.training.springcore.task4_6.service.PositionService;
import com.epam.java.training.springcore.task4_6.service.SalaryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.doThrow;

/**
 * Created by Vladislav Zhelezov.
 */
public class SalaryServiceExceptionTests {

    @Mock
    PositionService positionService;
    @InjectMocks
    SalaryService salaryService;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = IllegalStateException.class)
    public void testSalaryServiceInflationOccurredForThisPosition_ShouldReturnIllegalStateException() {
        doThrow(IllegalStateException.class).when(positionService).getPositions();
        salaryService.inflationOccurredForThisPosition();
    }

    @Test(expected = IllegalStateException.class)
    public void testSalaryServiceCourseDollar_ShouldReturnIllegalStateException() {
        doThrow(IllegalStateException.class).when(positionService).getPositions();
        salaryService.courseDollar(5);
    }
}
