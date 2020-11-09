package com.epam.java.training.spring_boot.task1.service;

import com.epam.java.training.spring_boot.task1.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PositionService {


    Map<Integer, Position> positions;

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

}
