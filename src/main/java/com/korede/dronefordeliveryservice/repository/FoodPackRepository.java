package com.korede.dronefordeliveryservice.repository;

import com.korede.dronefordeliveryservice.model.FoodPack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface FoodPackRepository extends JpaRepository<FoodPack, String> {
}
