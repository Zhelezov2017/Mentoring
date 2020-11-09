package com.epam.java.training.springcore.task4_6.service;

import com.epam.java.training.springcore.task4_6.model.Salary;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Vladislav Zhelezov.
 */
public class SalaryService {

    private PositionService positionService;

    public void inflationOccurredForThisPosition() {
        //grow on 5%
        positionService.getPositions().forEach((e, p) -> {
            int currentSalaryInDollars = p.getSalary().getSalaryInDollars() * (100 + 5) / 100;
            p.setSalary(new Salary(currentSalaryInDollars));
            positionService.update(e, p);
        });
    }

    //How much dollar fall/hire
    public void courseDollar(Integer fall) {
        positionService.getPositions().forEach((e, p) -> {
            Integer salary = p.getSalary().getSalaryInDollars();
            //own course - if fall
            p.setSalary(new Salary(salary * (60 - fall) / 60));
            positionService.update(e, p);
        });
    }

    @Autowired
    public void setPositionService(PositionService positionService) {
        this.positionService = positionService;
    }

}
