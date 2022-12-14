package com.korede.dronefordeliveryservice.repository;

import com.korede.dronefordeliveryservice.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DroneRepository extends JpaRepository<Drone, String> {
}
