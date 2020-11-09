package com.epam.java.training.spring_boot.task5_6.repo;


import com.epam.java.training.spring_boot.task5_6.data.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentRepo extends JpaRepository<Agent, Long> {

    @Query("SELECT DISTINCT agent " +
            "FROM Agent agent " +
            "INNER JOIN RealEstate re ON re.agent.id = agent.id " +
            "WHERE EXTRACT(MONTH FROM re.soldDate) = :numberMonth AND re.sold = true "
    )
    List<Agent> getAgentsWhichSoldRealEstateInThisMonth(@Param("numberMonth") Integer numberMonth);
}
