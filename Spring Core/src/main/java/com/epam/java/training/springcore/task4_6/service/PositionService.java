package com.epam.java.training.springcore.task4_6.service;

import com.epam.java.training.springcore.task4_6.model.Position;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by Vladislav Zhelezov.
 */
public class PositionService {

    private Map<Integer, Position> positions;

    @Autowired
    public PositionService(Map<Integer, Position> positions) {
        this.positions = positions;
    }


    public void create(Integer key, Position position) {
        if (position == null) throw new NullPointerException("Position is null!");
        positions.put(key, position);
    }

    public Position read(Integer key) {
        if (positions.get(key) == null) throw new NullPointerException("Position is null!");
        return positions.get(key);
    }

    public Position update(Integer key, Position position) {
        if (position == null) throw new NullPointerException("Position is null!");
        if (positions.get(key) == null) {
            positions.put(key, position);
            return positions.get(key);
        }
        if (positions.get(key).equals(position)) {
            return positions.get(key);
        } else {
            positions.remove(key);
            positions.put(key, position);
            return position;
        }
    }

    public void delete(Integer key) {
        if (positions.get(key) == null) throw new NullPointerException("Position is null!");
        positions.remove(key);
    }

    public Map<Integer, Position> getPositions() {
        return positions;
    }
}
