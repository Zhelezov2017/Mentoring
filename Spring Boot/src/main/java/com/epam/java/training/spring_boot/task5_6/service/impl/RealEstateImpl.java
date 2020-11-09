package com.epam.java.training.spring_boot.task5_6.service.impl;


import com.epam.java.training.spring_boot.task5_6.data.entity.RealEstate;
import com.epam.java.training.spring_boot.task5_6.repo.RealEstateRepo;
import com.epam.java.training.spring_boot.task5_6.service.RealEstateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service(RealEstateImpl.NAME)
@RequiredArgsConstructor
public class RealEstateImpl implements RealEstateService {
    static final String NAME = "RealEstateImpl";
    private final RealEstateRepo realEstateRepo;

    @Override
    public List<RealEstate> getAll() {
        log.info("Get all real estates");
        return realEstateRepo.findAll();
    }

    @Override
    public RealEstate getById(Long id) {
        log.info("Get real estate with id: " + id);
        return realEstateRepo.findById(id)
                .orElse(null);
    }

    @Override
    @Transactional
    public RealEstate updateRealEstate(RealEstate realEstate) {
        log.info("Update real estate: " + realEstate);
        RealEstate entity = realEstateRepo.findById(realEstate.getId())
                .orElseThrow(() -> new NullPointerException("Real estate with id: " + realEstate.getId() + " not found"));
        entity.setAgent(realEstate.getAgent());
        entity.setName(realEstate.getName());
        entity.setNumberOfRoom(realEstate.getNumberOfRoom());
        entity.setCountOfViews(realEstate.getCountOfViews());
        entity.setPrice(realEstate.getPrice());
        entity.setSold(realEstate.getSold());
        if (Objects.nonNull(realEstate.getSoldDate())) {
            entity.setSoldDate(realEstate.getSoldDate());
        }
        return realEstateRepo.save(entity);
    }

    @Override
    public RealEstate createRealEstate(RealEstate realEstate) {
        log.info("Create real estate: " + realEstate);
        return realEstateRepo.save(realEstate);
    }

    @Override
    public RealEstate deleteRealEstate(Long id) {
        log.info("Delete real estate with id: " + id);
        RealEstate realEstate = realEstateRepo.findById(id)
                .orElseThrow(() -> new NullPointerException("Real estate with id: " + id + " not found"));
        realEstateRepo.delete(realEstate);
        return realEstate;
    }

    @Override
    @Transactional
    public RealEstate buyRealEstate(RealEstate realEstate) {
        realEstate.setSold(Boolean.TRUE);
        realEstate.setSoldDate(LocalDate.now());
        return realEstateRepo.save(realEstate);
    }

    @Override
    @Scheduled(fixedDelay = 30_000L)
    public void toChangesPrice() {
        List<RealEstate> realEstates = realEstateRepo.findAll();
        double random = -1 + Math.random() * 2;
        List<RealEstate> realEstatesNew = realEstates.stream()
                .peek(realEstate -> realEstate.setPrice(realEstate.getPrice() - (realEstate.getPrice() * 0.01 * random)))
                .peek(realEstate -> log.info("Real estate with id: " + realEstate.getId() + " changed in price."))
                .collect(Collectors.toList());
        realEstateRepo.saveAll(realEstatesNew);
    }

    @Override
    public List<RealEstate> getBestRealEstates() {
        return realEstateRepo.getBestRealEstates();
    }
}
