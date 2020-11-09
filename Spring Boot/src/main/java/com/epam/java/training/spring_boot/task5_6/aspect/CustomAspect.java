package com.epam.java.training.spring_boot.task5_6.aspect;

import com.epam.java.training.spring_boot.task5_6.data.entity.RealEstate;
import com.epam.java.training.spring_boot.task5_6.service.RealEstateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Aspect
@Component
public class CustomAspect {

    private final RealEstateService realEstateService;

    @AfterReturning(value = "execution(* com.epam.java.training.spring_boot.task5_6.service.impl.RealEstateImpl+.getById(..))",
            returning = "realEstate")
    public void setCountOfView(RealEstate realEstate) {
        Long newCountOfViews = realEstate.getCountOfViews() + 1L;
        realEstate.setCountOfViews(newCountOfViews);
        realEstateService.updateRealEstate(realEstate);
    }
}
