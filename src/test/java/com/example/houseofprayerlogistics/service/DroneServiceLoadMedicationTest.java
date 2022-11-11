package com.example.houseofprayerlogistics.service;

import com.example.houseofprayerlogistics.config.KwikLogisticProperties.BatteryProperties;
import com.example.houseofprayerlogistics.data.TestData;
import com.example.houseofprayerlogistics.entity.Drone;
import com.example.houseofprayerlogistics.enums.Model;
import com.example.houseofprayerlogistics.enums.State;
import com.example.houseofprayerlogistics.repository.DroneRepository;
import com.example.houseofprayerlogistics.repository.MedicationOrderRepository;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DroneServiceLoadMedicationTest {

  @InjectMocks
  private DroneService droneServiceMock;

  @Mock
  private DroneRepository droneRepository;

  @Mock
  private BatteryProperties batteryProperties;

  @Mock
  private MedicationOrderRepository medicationOrderRepository;

  @Mock
  Drone droneExists;

  private String serialNumber;

  @Before
  public void init(){
    serialNumber = "XVH5547674734UTPO";
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testLoadDroneWithMedicationWhenAllItemsAreWithinDifferentAgeBuckets(){
    Drone drone = Drone.builder().id(1L)
        .batteryCapacity((byte) 25)
        .weightLimit((short) 500)
        .serialNumber("XV345PG")
        .state(State.IDLE)
        .model(Model.CRUISERWEIGHT)
        .build();

    Mockito.when(droneRepository.findDroneBySerialNumber(serialNumber)).thenReturn(Optional.of(drone));
    Mockito.when(batteryProperties.getMinCapacity()).thenReturn(25);
    Mockito.when(medicationOrderRepository.findAllByState(State.IDLE))
        .thenReturn(Optional.of(TestData.getMedicationOrderDataSetForLoadItemsWithinDifferentAgeBracket()));
    Mockito.when(droneExists.getState()).thenReturn(State.IDLE);

    Assert.assertTrue(droneServiceMock.loadDroneWithItems(serialNumber));
  }

  @Test
  public void testLoadDroneWithMedicationWhenAllItemsAreWithinSameAgeBuckets(){
    Drone drone = Drone.builder().id(1L)
        .batteryCapacity((byte) 25)
        .weightLimit((short) 500)
        .serialNumber("XV345PG")
        .state(State.IDLE)
        .model(Model.CRUISERWEIGHT)
        .build();

    Mockito.when(droneRepository.findDroneBySerialNumber(serialNumber)).thenReturn(Optional.of(drone));
    Mockito.when(batteryProperties.getMinCapacity()).thenReturn(25);
    Mockito.when(medicationOrderRepository.findAllByState(State.IDLE))
        .thenReturn(Optional.of(TestData.getMedicationOrderDataSetForLoadItemsWithinSameAgeBracket()));
    Mockito.when(droneExists.getState()).thenReturn(State.IDLE);

    Assert.assertTrue(droneServiceMock.loadDroneWithItems(serialNumber));
  }

}
