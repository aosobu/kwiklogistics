package com.example.houseofprayerlogistics.repository;


import com.example.houseofprayerlogistics.entity.MedicationOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationOrderRepository extends JpaRepository<MedicationOrder, Long> {

}
