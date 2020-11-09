package com.epam.java.training.springcore.task4_6.service;

import com.epam.java.training.springcore.task4_6.model.Position;
import com.epam.java.training.springcore.task4_6.model.Salary;
import com.epam.java.training.springcore.task4_6.model.Skill;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vladislav Zhelezov.
 */
public class PositionServiceTest {

    private Map<Integer, Position> positions = new HashMap<>();

    private PositionService positionService = new PositionService(positions);


    @Before
    public void data() {
        positions.put(1, new Position("junior", new Salary(1000), Collections.singletonList(new Skill("Java", 4))));
        positions.put(2, new Position("junior", new Salary(1000), Collections.singletonList(new Skill("Java", 4))));
        positions.put(3, new Position("junior", new Salary(1000), Collections.singletonList(new Skill("Java", 4))));
        positions.put(4, new Position("junior", new Salary(1000), Collections.singletonList(new Skill("Java", 4))));
    }


    @Test
    public void create() {
        positions.forEach((e, p) -> System.out.println(p));
        positionService.create(5, null);
    }

    @Test
    public void read() {
        positionService.read(6);
    }

    @Test
    public void update() {
        positionService.update(4, null);
    }

    @Test
    public void delete() {
        positionService.delete(26);
    }
}