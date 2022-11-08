package com.example.houseofprayerlogistics.repository;

import com.example.houseofprayerlogistics.entity.Medication;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {
  Optional<Medication> findMedicationByCode(String code);
}
