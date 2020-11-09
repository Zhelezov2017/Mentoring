package com.epam.java.training.spring_boot.task5_6.service;

import com.epam.java.training.spring_boot.task5_6.data.entity.RealEstate;

import java.util.List;


public interface RealEstateService {

    List<RealEstate> getAll();

    RealEstate getById(Long id);

    RealEstate updateRealEstate(RealEstate realEstate);

    RealEstate createRealEstate(RealEstate realEstate);

    RealEstate deleteRealEstate(Long id);

    RealEstate buyRealEstate(RealEstate realEstate);

    void toChangesPrice();

    List<RealEstate> getBestRealEstates();

}
