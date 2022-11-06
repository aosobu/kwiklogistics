package com.example.houseofprayerlogistics.repository;


import com.example.houseofprayerlogistics.entity.Drone;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {
  Optional<Drone> findDroneBySerialNumber(String serialNumber);
}
