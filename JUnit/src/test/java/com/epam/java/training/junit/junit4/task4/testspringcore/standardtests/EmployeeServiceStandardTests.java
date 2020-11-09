package com.epam.java.training.junit.junit4.task4.testspringcore.standardtests;

import com.epam.java.training.springcore.task4_6.model.Employee;
import com.epam.java.training.springcore.task4_6.model.Position;
import com.epam.java.training.springcore.task4_6.model.Salary;
import com.epam.java.training.springcore.task4_6.model.Skill;
import com.epam.java.training.springcore.task4_6.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Set;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Vladislav Zhelezov.
 */
@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceStandardTests {

    @Spy
    private Set<Employee> employees;
    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void testEmployeeServiceHire_hireShouldBeCall4Time() {
        when(employees.add(any())).thenReturn(Boolean.TRUE);
        Position position = new Position("Engineer",
                new Salary(5000), Collections.singletonList(new Skill("Java", 400)));
        Employee inna = new Employee(3, "Inna", 31, "Grow", position);

        employeeService.hire(inna, position);


        verify(employees, times(1)).add(any());

    }
}
