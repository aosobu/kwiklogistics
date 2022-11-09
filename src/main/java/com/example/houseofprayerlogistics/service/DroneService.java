package com.example.houseofprayerlogistics.service;

import com.example.houseofprayerlogistics.constants.AppConstants;
import com.example.houseofprayerlogistics.dto.DroneDTO;
import com.example.houseofprayerlogistics.entity.Drone;
import com.example.houseofprayerlogistics.entity.Medication;
import com.example.houseofprayerlogistics.entity.MedicationOrder;
import com.example.houseofprayerlogistics.enums.State;
import com.example.houseofprayerlogistics.repository.DroneRepository;
import com.example.houseofprayerlogistics.repository.MedicationOrderRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DroneService {

  private ModelMapper modelMapper;
  private DroneRepository droneRepository;
  private MedicationOrderRepository medicationOrderRepository;

  @Autowired
  public DroneService(ModelMapper modelMapper,
      DroneRepository droneRepository,
      MedicationOrderRepository medicationOrderRepository) {
    this.modelMapper = modelMapper;
    this.droneRepository = droneRepository;
    this.medicationOrderRepository = medicationOrderRepository;
  }

  public boolean createDrone(DroneDTO droneDTO){
    Drone drone = modelMapper.map(droneDTO, Drone.class);
    Optional<Drone> droneCopy = droneRepository.findDroneBySerialNumber(droneDTO.getSerialNumber());
    if(!droneCopy.isPresent()){
      droneRepository.save(drone);
      return true;
    }
    return false;
  }

  public boolean loadDrone(String serialNumber){
    Optional<Drone> drone = droneRepository.findDroneBySerialNumber(serialNumber);
    if(!drone.isPresent()){
      AppConstants.MESSAGE = AppConstants.DRONE_NOT_EXIST;
      return false;
    }

    if(drone.get().getBatteryCapacity() >= AppConstants.MIN_BATTERY_CAPACITY){
      Optional<List<MedicationOrder>> medicationOrderList = medicationOrderRepository.findAllByState(State.IDLE.getState());
      List<Medication> medicationList = new ArrayList<>();

      if(medicationOrderList.isPresent()){
        List<MedicationOrder> medicationOrders = medicationOrderList.get();
        medicationOrders.forEach(medicationOrder -> {
          medicationList.add(medicationOrder.getMedication());
        });
        return loadDroneWithMedication(medicationList);
      }
    }

    return false;
  }

  public boolean loadDroneWithMedication(List<Medication> medicationList){
    HashMap<Short, List<Medication>> ageBasedMedicationMap = generateAgeBasedMedicationMap(medicationList);
    return false;
  }

  public HashMap<Short, List<Medication>> generateAgeBasedMedicationMap(List<Medication> medicationList){
    HashMap<Short, List<Medication>> ageBasedMedicationMap = new HashMap<>();
    Collections.sort(medicationList);
    boolean start = true;

    int j = 0;
    while(start){
      List<Medication> tempList = new ArrayList<>();

      for(int i=j; i<medicationList.size();){

        short key = medicationList.get(i).getAge();
        tempList.add(medicationList.get(i));

        if(ageBasedMedicationMap.containsKey(key)){
          tempList = ageBasedMedicationMap.get(key);
          tempList.add(medicationList.get(i));
          ageBasedMedicationMap.put(key,tempList);
        }else{
          ageBasedMedicationMap.put(key,tempList);
        }
        if(i == medicationList.size() - 1)
          start = false;
        j++;
        break;
      }
    }

    return ageBasedMedicationMap;
  }

}
