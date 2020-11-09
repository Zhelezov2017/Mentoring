package com.epam.java.training.junit.junit4.task4.testspringcore.testswithtimeout;

import com.epam.java.training.springcore.task4_6.model.Position;
import com.epam.java.training.springcore.task4_6.model.Salary;
import com.epam.java.training.springcore.task4_6.model.Skill;
import com.epam.java.training.springcore.task4_6.service.PositionService;
import com.epam.java.training.springcore.task4_6.service.SalaryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import static org.mockito.Mockito.*;

/**
 * Created by Vladislav Zhelezov.
 */
public class SalaryServiceWithTimeout {

    @Mock
    PositionService positionService;

    @InjectMocks
    SalaryService salaryService;

    private HashMap<Integer, Position> idToPosition;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        HashMap<Integer, Skill> skillMap = new HashMap<Integer, Skill>() {{
            put(1, new Skill("Java", 4));
            put(2, new Skill("Python", 3));
        }};
        idToPosition = new HashMap<Integer, Position>() {{
            put(1, new Position("junior", new Salary(1000), Collections.singletonList(skillMap.get(1))));
            put(2, new Position("engineer", new Salary(3000), Arrays.asList(skillMap.get(2), skillMap.get(1))));
        }};
    }


    @Test(timeout = 100)
    public void testSalaryServiceInflationOccurredForThisPosition_ShouldReturnMapNormalValues() {
        when(positionService.getPositions()).thenReturn(idToPosition);
        salaryService.inflationOccurredForThisPosition();

        verify(positionService, times(1)).getPositions();
    }

    @Test(timeout = 500)
    public void testSalaryServiceCourseDollars_ShouldReturnMapNormalValues() {
        when(positionService.getPositions()).thenReturn(idToPosition);
        salaryService.courseDollar(7);

        verify(positionService, times(1)).getPositions();
    }
}
