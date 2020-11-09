package com.epam.java.training.spring_boot.task5_6.rest;

import com.epam.java.training.spring_boot.task5_6.data.entity.RealEstate;
import com.epam.java.training.spring_boot.task5_6.service.RealEstateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/realEstate")
@RequiredArgsConstructor
public class RealEstateController {

    private final RealEstateService realEstateService;

    @GetMapping("/getAll")
    public List<RealEstate> getAll() {
        return realEstateService.getAll();
    }

    @GetMapping("/{id}")
    public RealEstate getRealEstateById(@PathVariable("id") Long id) {
        return realEstateService.getById(id);
    }

    @PostMapping
    public RealEstate saveRealEstate(@RequestBody RealEstate realEstate) {
        return realEstateService.createRealEstate(realEstate);
    }

    @PutMapping
    public RealEstate updateRealEstate(@RequestBody RealEstate realEstate) {
        return realEstateService.updateRealEstate(realEstate);
    }

    @DeleteMapping("/{id}")
    public RealEstate deleteRealEstate(@PathVariable("realEstateId") Long realEstateId) {
        return realEstateService.deleteRealEstate(realEstateId);
    }

    @PostMapping("/buyRealEstate")
    public RealEstate buyRealEstate(@RequestBody RealEstate realEstate) {
        return realEstateService.buyRealEstate(realEstate);
    }

    @GetMapping("/getTheFiveBestRealEstate")
    public List<RealEstate> getTheFiveBestRealEstate() {
        return realEstateService.getBestRealEstates();
    }
}
