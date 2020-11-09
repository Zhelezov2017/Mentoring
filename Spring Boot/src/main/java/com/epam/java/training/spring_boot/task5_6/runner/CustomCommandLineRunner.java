package com.epam.java.training.spring_boot.task5_6.runner;


import com.epam.java.training.spring_boot.task5_6.data.entity.Agent;
import com.epam.java.training.spring_boot.task5_6.data.entity.RealEstate;
import com.epam.java.training.spring_boot.task5_6.service.AgentService;
import com.epam.java.training.spring_boot.task5_6.service.RealEstateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@Order(2)
@RequiredArgsConstructor
public class CustomCommandLineRunner implements CommandLineRunner {

    @Autowired
    private final RealEstateService realEstateService;
    @Autowired
    private final AgentService agentService;

    @Override
    public void run(String... args) throws Exception {
        RealEstate realEstate = realEstateService.getById(1L);
        realEstateService.buyRealEstate(realEstate);
        log.info("Real estate with id: " + realEstate.getId());
        log.info("Real estate with id have count of view: " + realEstate.getCountOfViews());

        List<RealEstate> realEstates = agentService.allSoldRealEstateForAgent(1L);
        log.info("All sold real estates for id 1: " + realEstates.toString());

        List<RealEstate> theFiveBestRealEstates = realEstateService.getBestRealEstates();
        log.info("The Five Best Real estates: " + theFiveBestRealEstates.toString());

        Map<Month, Map<Agent, Double>> theFiveBestAgents = agentService.getTheFiveBestAgents(LocalDate.of(2010, 7, 1),
                LocalDate.of(2010, 7, 31));
        log.info("The Five Best Agents: " + theFiveBestAgents.toString());
    }
}
