package com.example.houseofprayerlogistics.repository;


import com.example.houseofprayerlogistics.entity.Drone;
import com.example.houseofprayerlogistics.enums.State;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {
  Optional<Drone> findDroneBySerialNumber(String serialNumber);
  Optional<List<Drone>> findAllByState(State state);
}
