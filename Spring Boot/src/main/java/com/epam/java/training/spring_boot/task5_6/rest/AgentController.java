package com.epam.java.training.spring_boot.task5_6.rest;

import com.epam.java.training.spring_boot.task5_6.data.entity.Agent;
import com.epam.java.training.spring_boot.task5_6.data.entity.RealEstate;
import com.epam.java.training.spring_boot.task5_6.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/agent")
@RequiredArgsConstructor
public class AgentController {

    private final AgentService agentService;

    @GetMapping("/getAll")
    public List<Agent> getAll() {
        return agentService.getAll();
    }

    @GetMapping("/{id}")
    public Agent getAgentById(@PathVariable("id") Long id) {
        return agentService.getById(id);
    }

    @PostMapping
    public Agent saveAgent(@Valid @RequestBody Agent agent) {
        return agentService.createAgent(agent);
    }

    @PutMapping
    public Agent updateAgent(@Valid @RequestBody Agent agent) {
        return agentService.updateAgent(agent);
    }

    @DeleteMapping("/{id}")
    public Agent deleteAgent(@PathVariable("agentId") Long agentId) {
        return agentService.deleteAgent(agentId);
    }

    @PutMapping("/{realEstateId}/{agentId}")
    public Agent hireAgent(@PathVariable("agentId") Long agentId,
                           @PathVariable("realEstateId") Long realEstateId) {
        return agentService.hireAgent(agentId, realEstateId);
    }

    @GetMapping("/allSoldRealEstateForAgent/{agentId}")
    public List<RealEstate> getAllSoldRealEstateForAgent(@PathVariable("agentId") Long agentId) {
        return agentService.allSoldRealEstateForAgent(agentId);
    }

    @GetMapping("/getTheFiveBestAgents")
    public Map<Month, Map<Agent, Double>> getTheFiveBestAgents(@RequestParam(name = "fromDate") LocalDate fromDate,
                                                        @RequestParam(name = "toDate") LocalDate toDate) {
        return agentService.getTheFiveBestAgents(fromDate, toDate);
    }

}
