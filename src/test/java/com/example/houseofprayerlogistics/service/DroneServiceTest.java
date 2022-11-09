package com.example.houseofprayerlogistics.service;

import com.example.houseofprayerlogistics.entity.Medication;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DroneServiceTest {

  private List<Medication> medicationList = new ArrayList<>();

  @Before
  public void init(){
    medicationList = new ArrayList<>();
    medicationList.add(Medication.builder().id(1l).weight((short) 100).age((short) 1).name("augmentin-100g").code("'aug-90'").build());
    medicationList.add(Medication.builder().id(2l).weight((short) 200).age((short) 0).name("augmentin-100g").code("'aug-90'").build());
    medicationList.add(Medication.builder().id(3l).weight((short) 100).age((short) 9).name("augmentin-100g").code("'aug-90'").build());
    medicationList.add(Medication.builder().id(4l).weight((short) 300).age((short) 9).name("augmentin-100g").code("'aug-90'").build());
    medicationList.add(Medication.builder().id(5l).weight((short) 200).age((short) 2).name("augmentin-100g").code("'aug-90'").build());
    medicationList.add(Medication.builder().id(6l).weight((short) 400).age((short) 3).name("augmentin-100g").code("'aug-90'").build());
    medicationList.add(Medication.builder().id(7l).weight((short) 100).age((short) 4).name("augmentin-100g").code("'aug-90'").build());
    medicationList.add(Medication.builder().id(8l).weight((short) 300).age((short) 2).name("augmentin-100g").code("'aug-90'").build());
    medicationList.add(Medication.builder().id(9l).weight((short) 500).age((short) 8).name("augmentin-100g").code("'aug-90'").build());
  }

  @Test
  public void testAgedBasedMedicationMapGeneration(){
    DroneService droneService = new DroneService(null, null, null);
    HashMap<Short, List<Medication>> ageBasedMedicationMap = droneService.generateAgeBasedMedicationMap(medicationList);

    AtomicInteger count = new AtomicInteger();
    ageBasedMedicationMap.entrySet().forEach(key->{
      count.getAndIncrement();
    });


    Short key = 0;
    Assert.assertEquals(ageBasedMedicationMap.get(key).size(), 1);
    key = 1;
    Assert.assertEquals(ageBasedMedicationMap.get(key).size(), 1);
    key = 2;
    Assert.assertEquals(ageBasedMedicationMap.get(key).size(), 2);
    key = 3;
    Assert.assertEquals(ageBasedMedicationMap.get(key).size(), 1);
    key = 4;
    Assert.assertEquals(ageBasedMedicationMap.get(key).size(), 1);
    key = 8;
    Assert.assertEquals(ageBasedMedicationMap.get(key).size(), 1);
    key = 9;
    Assert.assertEquals(ageBasedMedicationMap.get(key).size(), 2);
    Assert.assertEquals(count.get(), 7);
  }
}
