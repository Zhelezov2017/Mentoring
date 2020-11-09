package com.epam.java.training.spring_boot.task5_6.runner;


import com.epam.java.training.spring_boot.task5_6.data.entity.Agent;
import com.epam.java.training.spring_boot.task5_6.data.entity.RealEstate;
import com.epam.java.training.spring_boot.task5_6.service.AgentService;
import com.epam.java.training.spring_boot.task5_6.service.RealEstateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
@Order(1)
@RequiredArgsConstructor
public class CustomApplicationRunner implements ApplicationRunner {

    @Autowired
    private final AgentService agentService;
    @Autowired
    private final RealEstateService realEstateService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Agent vlad = getAgentVlad();
        Agent andrey = getAgentAndrey();
        Agent anton = getAgentAnton();
        agentService.createAgent(vlad);
        agentService.createAgent(andrey);
        agentService.createAgent(anton);
        log.info("Agent with id: " + vlad.getId() + " created");

        List<Agent> allAgents = agentService.getAll();
        log.info("All agents: " + allAgents.toString());

        andrey.setAgentLevel(2L);
        agentService.updateAgent(andrey);
        log.info("Level Andrey 2!");

        RealEstate apartmentOne = getApartmentRealEstateOne();
        RealEstate apartmentTwo = getApartmentRealEstateTwo();
        RealEstate apartmentThree = getApartmentRealEstateThree();
        realEstateService.createRealEstate(apartmentOne);
        log.info("Real estate with id: " + apartmentOne.getId() + "created");
        realEstateService.createRealEstate(apartmentTwo);
        realEstateService.createRealEstate(apartmentThree);

        agentService.hireAgent(1L, 2L);
        agentService.hireAgent(1L, 1L);
        log.info("Hired agent with id 1");
        agentService.hireAgent(3L, 3L);
    }


    private Agent getAgentVlad() {
        Agent agent = new Agent();
        agent.setFirstName("Vlad");
        agent.setLastName("Zhelezov");
        agent.setId(1L);
        agent.setAgentLevel(3L);
        return agent;
    }

    private Agent getAgentAndrey() {
        Agent agent = new Agent();
        agent.setFirstName("Andrey");
        agent.setLastName("Korov");
        agent.setId(2L);
        agent.setAgentLevel(1L);
        return agent;
    }

    private Agent getAgentAnton() {
        Agent agent = new Agent();
        agent.setFirstName("Anton");
        agent.setLastName("Boron");
        agent.setId(3L);
        agent.setAgentLevel(2L);
        return agent;
    }

    private RealEstate getApartmentRealEstateOne() {
        RealEstate apartment = new RealEstate();
        apartment.setSold(false);
        apartment.setNumberOfRoom(4L);
        apartment.setPrice(30000D);
        apartment.setId(1L);
        return apartment;
    }

    private RealEstate getApartmentRealEstateTwo() {
        RealEstate apartment = new RealEstate();
        apartment.setSold(true);
        apartment.setSoldDate(LocalDate.of(2020, 7, 5));
        apartment.setNumberOfRoom(3L);
        apartment.setPrice(40000D);
        apartment.setId(2L);
        return apartment;
    }

    private RealEstate getApartmentRealEstateThree() {
        RealEstate apartment = new RealEstate();
        apartment.setSold(true);
        apartment.setSoldDate(LocalDate.of(2020, 7, 9));
        apartment.setNumberOfRoom(4L);
        apartment.setPrice(50000D);
        apartment.setId(3L);
        return apartment;
    }
}
