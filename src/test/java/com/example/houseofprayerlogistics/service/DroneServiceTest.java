package com.example.houseofprayerlogistics.service;

import com.example.houseofprayerlogistics.data.TestData;
import com.example.houseofprayerlogistics.entity.Drone;
import com.example.houseofprayerlogistics.entity.Medication;
import com.example.houseofprayerlogistics.entity.MedicationOrder;
import com.example.houseofprayerlogistics.enums.Model;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DroneServiceTest {

  private DroneService droneService;

  @Before
  public void init() {
    droneService = new DroneService(null, null, null, null);
  }

  @Test
  public void testAgedBasedMedicationMapGeneration(){
    short key = 9;
    AtomicInteger count = new AtomicInteger();

    Map<Short, List<MedicationOrder>> ageBasedMedicationOrderMap = droneService.generateAgeBasedMedicationOrderMap(
                                                                                TestData.getMedicationOrderDataSetForLoadItemsWithinDifferentAgeBracket());
    ageBasedMedicationOrderMap.forEach((key1, value) -> count.getAndIncrement());

    Assert.assertEquals( 2, ageBasedMedicationOrderMap.get(key).size());
    key = 8;
    Assert.assertEquals( 1, ageBasedMedicationOrderMap.get(key).size());
    key = 5;
    Assert.assertEquals( 1, ageBasedMedicationOrderMap.get(key).size());
    key = 4;
    Assert.assertEquals( 2, ageBasedMedicationOrderMap.get(key).size());
    key = 1;
    Assert.assertEquals( 1, ageBasedMedicationOrderMap.get(key).size());
    Assert.assertEquals( 5, count.get());
  }

  @Test
  public void testLoadDroneWithMedicationWhenAllItemsAreWithinDifferentAgeBuckets(){
    Drone drone = Drone.builder().id(1L)
        .batteryCapacity((byte) 25)
        .weightLimit((short) 500)
        .serialNumber("XV345PG")
        .model(Model.CRUISERWEIGHT)
        .build();

    List<Medication> loadItems = droneService.loadDroneWithMedication(TestData.getMedicationDataSetForLoadItemsWithinDifferentAgeBracket(), drone);
    Collections.sort(loadItems);

    //assert values
    Assert.assertEquals(300, loadItems.get(0).getProfit());
    Assert.assertEquals(200, loadItems.get(1).getProfit());

    //assert weights
    Assert.assertEquals(300, loadItems.get(0).getWeight());
    Assert.assertEquals(200, loadItems.get(1).getWeight());
  }
}