package com.epam.java.training.spring_boot.task1.service;


import com.epam.java.training.spring_boot.task1.model.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryService {

    private PositionService positionService;

    public void inflationOccurredForThisPosition() {
        //grow on 5%
        positionService.positions.forEach((e, p) -> {
            int currentSalaryInDollars = p.getSalary().getSalaryInDollars() * (100 + 5) / 100;
            p.setSalary(new Salary(currentSalaryInDollars));
            positionService.positions.put(e, p);
        });
    }

    //How much dollar fall/hire
    public void courseDollar(Integer fall) {
        positionService.positions.forEach((e, p) -> {
            Integer salary = p.getSalary().getSalaryInDollars();
            //own course - if fall
            p.setSalary(new Salary(salary * (60 - fall) / 60));
            positionService.positions.put(e, p);
        });
    }

    @Autowired
    public void setPositionService(PositionService positionService) {
        this.positionService = positionService;
    }

}
