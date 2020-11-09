package com.epam.java.training.spring_boot.task5_6.service.impl;


import com.epam.java.training.spring_boot.task5_6.data.entity.Agent;
import com.epam.java.training.spring_boot.task5_6.data.entity.RealEstate;
import com.epam.java.training.spring_boot.task5_6.repo.AgentRepo;
import com.epam.java.training.spring_boot.task5_6.service.AgentService;
import com.epam.java.training.spring_boot.task5_6.service.RealEstateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service(AgentServiceImpl.NAME)
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {
    static final String NAME = "AgentServiceImpl";
    private final AgentRepo agentRepo;
    private final RealEstateService realEstateService;


    @Override
    public List<Agent> getAll() {
        log.info("Get all agents");
        return agentRepo.findAll();
    }

    @Override
    public Agent getById(Long id) {
        log.info("Get agent with id: " + id);
        return agentRepo.findById(id)
                .orElseThrow(() -> new NullPointerException("Agent with id: " + id + " not found"));
    }

    @Override
    @Transactional
    public Agent updateAgent(Agent agent) {
        log.info("Update agent: " + agent);
        Agent entity = agentRepo.findById(agent.getId())
                .orElseThrow(() -> new NullPointerException("Agent with id: " + agent.getId() + " not found"));
        entity.setFirstName(agent.getFirstName());
        entity.setLastName(agent.getLastName());
        entity.setRealEstate(agent.getRealEstate());
        entity.setAgentLevel(agent.getAgentLevel());
        return agentRepo.save(entity);
    }

    @Override
    public Agent createAgent(Agent agent) {
        log.info("Create agent: " + agent);
        return agentRepo.save(agent);
    }

    @Override
    @Transactional
    public Agent deleteAgent(Long id) {
        log.info("Delete agent with id: " + id);
        Agent agent = agentRepo.findById(id)
                .orElseThrow(() -> new NullPointerException("Agent with id: " + id + " not found"));
        agentRepo.delete(agent);
        return agent;
    }

    @Override
    @Transactional
    public Agent hireAgent(Long agentId, Long realEstateId) {
        Agent agent = agentRepo.findById(agentId)
                .orElseThrow(() -> new NullPointerException("Agent with id: " + agentId + " not found"));
        RealEstate realEstate = realEstateService.getById(realEstateId);
        agent.getRealEstate().add(realEstate);
        realEstate.setAgent(agent);
        realEstateService.updateRealEstate(realEstate);
        return agent;
    }

    @Override
    public List<RealEstate> allSoldRealEstateForAgent(Long id) {
        return agentRepo.findById(id)
                .map(Agent::getRealEstate)
                .orElse(new ArrayList<>())
                .stream()
                .filter(realEstate -> realEstate.getSold().equals(Boolean.TRUE))
                .collect(Collectors.toList());
    }



    @Override
    public Map<Month, Map<Agent, Double>> getTheFiveBestAgents(LocalDate fromDate, LocalDate toDate) {
        return createMonthToAgentMap(fromDate, toDate);
    }

    private Map<Month, Map<Agent, Double>> createMonthToAgentMap(LocalDate fromDate, LocalDate toDate) {
        Map<Month, Map<Agent, Double>> monthToAgentMap = new HashMap<>();
        fromDate.datesUntil(toDate)
                .map(LocalDate::getMonth)
                .distinct()
                .forEach(month -> {
                    Map<Agent, Double> amountsForAgentNotSorted = getAmountsForAgent(month, new HashMap<>());
                    Map<Agent, Double> amountsForAgent = amountsForAgentNotSorted.entrySet().stream()
                            .sorted(Map.Entry.<Agent, Double>comparingByValue().reversed())
                            .limit(5)
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    monthToAgentMap.put(month, amountsForAgent);
                });
        return monthToAgentMap;
    }

    private Map<Agent, Double> getAmountsForAgent(Month month, Map<Agent, Double> amounts) {
        agentRepo.getAgentsWhichSoldRealEstateInThisMonth(month.getValue()).stream()
                .map(Agent::getRealEstate)
                .forEach(realEstates -> {
                    double sum = realEstates.stream()
                            .filter(realEstate -> realEstate.getSoldDate().getMonth().equals(month))
                            .mapToDouble(RealEstate::getPrice).sum();
                    amounts.put(realEstates.get(0).getAgent(), sum);
                });
        return amounts;
    }
}
