package com.example.houseofprayerlogistics.repository;


import com.example.houseofprayerlogistics.entity.MedicationOrder;
import com.example.houseofprayerlogistics.enums.State;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationOrderRepository extends JpaRepository<MedicationOrder, Long> {
  Optional<List<MedicationOrder>> findAllByState(State state);
}
