package com.epam.java.training.spring_boot.task5_6.service;


import com.epam.java.training.spring_boot.task5_6.data.entity.Agent;
import com.epam.java.training.spring_boot.task5_6.data.entity.RealEstate;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;

public interface AgentService {

    List<Agent> getAll();

    Agent getById(Long id);

    Agent updateAgent(Agent agent);

    Agent createAgent(Agent agent);

    Agent deleteAgent(Long id);

    Agent hireAgent(Long agentId, Long realEstateId);

    List<RealEstate> allSoldRealEstateForAgent(Long id);

    Map<Month, Map<Agent, Double>> getTheFiveBestAgents(LocalDate fromDate, LocalDate toDate);
}
