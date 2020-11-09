package com.epam.java.training.spring_boot.task5_6.repo;

import com.epam.java.training.spring_boot.task5_6.data.entity.RealEstate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RealEstateRepo extends JpaRepository<RealEstate, Long> {

    @Query("SELECT DISTINCT realEstate " +
            "FROM RealEstate realEstate " +
            "ORDER BY realEstate.price"
    )
    List<RealEstate> getBestRealEstates();
}
