package com.example.houseofprayerlogistics.service;

import com.example.houseofprayerlogistics.entity.Drone;
import com.example.houseofprayerlogistics.entity.Medication;
import com.example.houseofprayerlogistics.enums.Model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DroneServiceTest {

  private List<Medication> medicationList = new ArrayList<>();
  private DroneService droneService;

  @Before
  public void init(){
    droneService = new DroneService(null, null, null);
    medicationList = new ArrayList<>();

    medicationList.add(Medication.builder().id(1l).weight((short) 12).age((short) 1)
        .name("augmentin-100g").code("'aug-90'").value((short) 4).build());
    medicationList.add(Medication.builder().id(2l).weight((short) 1).age((short) 0)
        .name("augmentin-100g").code("'aug-90'").value((short) 2).build());
    medicationList.add(Medication.builder().id(3l).weight((short) 2).age((short) 9)
        .name("augmentin-100g").code("'aug-90'").value((short) 2).build());
    medicationList.add(Medication.builder().id(4l).weight((short) 1).age((short) 9)
        .name("augmentin-100g").code("'aug-90'").value((short) 1).build());
    medicationList.add(Medication.builder().id(5l).weight((short) 4).age((short) 2)
        .name("augmentin-100g").code("'aug-90'").value((short) 10).build());

    medicationList.add(Medication.builder().id(6l).weight((short) 400).age((short) 3)
        .name("augmentin-100g").code("'aug-90'").value((short) 4).build());
    medicationList.add(Medication.builder().id(7l).weight((short) 100).age((short) 4)
        .name("augmentin-100g").code("'aug-90'").build());
    medicationList.add(Medication.builder().id(8l).weight((short) 300).age((short) 2)
        .name("augmentin-100g").code("'aug-90'").build());
    medicationList.add(Medication.builder().id(9l).weight((short) 500).age((short) 8)
        .name("augmentin-100g").code("'aug-90'").build());
  }

  @Test
  public void testAgedBasedMedicationMapGeneration(){
    //prepare
    Short key = 0;
    AtomicInteger count = new AtomicInteger();

    //test
    HashMap<Short, List<Medication>> ageBasedMedicationMap = droneService.generateAgeBasedMedicationMap(medicationList);
    ageBasedMedicationMap.entrySet().forEach(keys->count.getAndIncrement());

    //assert
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

  @Test
  public void testLoadDroneWithMedication(){
    Drone drone = Drone.builder().id(1l)
        .batteryCapacity((byte) 25)
        .weightLimit((short) 15)
        .serialNumber("XV345PG")
        .model(Model.CRUISERWEIGHT)
        .build();

    List<Medication> loadItems = droneService.loadDroneWithMedication(medicationList.subList(0,5), drone);
    Collections.sort(loadItems);

    //assert values
    Assert.assertEquals(2, loadItems.get(0).getValue());
    Assert.assertEquals(10, loadItems.get(1).getValue());
    Assert.assertEquals(1, loadItems.get(2).getValue());
    Assert.assertEquals(2, loadItems.get(3).getValue());

    //assert weights
    Assert.assertEquals(1, loadItems.get(0).getWeight());
    Assert.assertEquals(4, loadItems.get(1).getWeight());
    Assert.assertEquals(1, loadItems.get(2).getWeight());
    Assert.assertEquals(2, loadItems.get(3).getWeight());

    //assert total weights and values
    Assert.assertEquals(15, loadItems.stream().map(Medication::getValue).mapToInt(Short::intValue).sum());
    Assert.assertEquals(8, loadItems.stream().map(Medication::getWeight).mapToInt(Short::intValue).sum());
  }
}
