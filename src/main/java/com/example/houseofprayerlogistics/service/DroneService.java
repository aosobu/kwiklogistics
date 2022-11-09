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
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
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
      AppConstants.message = AppConstants.DRONE_NOT_EXIST;
      return false;
    }

    if(drone.get().getBatteryCapacity() >= AppConstants.MIN_BATTERY_CAPACITY){
      Optional<List<MedicationOrder>> medicationOrderList = medicationOrderRepository.findAllByState(State.IDLE.getState());
      List<Medication> medicationList = new ArrayList<>();

      if(medicationOrderList.isPresent()){
        List<MedicationOrder> medicationOrders = medicationOrderList.get();
        medicationOrders.forEach(medicationOrder -> medicationList.add(medicationOrder.getMedication()));
        List<Medication> loadItems = loadDroneWithMedication(medicationList, drone.get());
      }
    }

    return false;
  }

  /**
   * HashMap<Short, List<Medication>> ageBasedMedicationMap = generateAgeBasedMedicationMap(medicationList);
   * @param medicationList
   * @param drone
   * @return
   */
  public List<Medication> loadDroneWithMedication(List<Medication> medicationList, Drone drone){
    int [][] possibleMedicationLoadMatrix = generateMedicationLoadMatrix(medicationList, drone);
    return getLoadItemsFromMatrix(possibleMedicationLoadMatrix, medicationList.size(),
        drone.getWeightLimit(), medicationList);
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

  public int[][] generateMedicationLoadMatrix(List<Medication> medicationList, Drone drone) {
    int numberOfItems = medicationList.size();
    int capacity = drone.getWeightLimit();

    // matrix stores the max value at each n-th item
    int[][] matrix = new int[numberOfItems + 1][capacity + 1];

    // first line is initialized to 0
    for (int i = 0; i <= capacity; i++)
      matrix[0][i] = 0;

    // iterating through items
    for (int i = 1; i <= numberOfItems; i++) {
      // we iterate on each capacity
      for (int j = 0; j <= capacity; j++) {
        if (medicationList.get(i-1).getWeight() > j) {
          matrix[i][j] = matrix[i - 1][j];
        }
        else {
          matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i - 1][j - medicationList.get(i-1).getWeight()]
              + medicationList.get(i-1).getValue());
        }
      }
    }

    return matrix;
  }

  public List<Medication> getLoadItemsFromMatrix(int[][] matrix,
      int numberOfItems,
      short capacity, List<Medication> medicationList){
    int result = matrix[numberOfItems][capacity];
    short droneWeightLimit = capacity;
    List<Medication> medicationItems = new ArrayList<>();

    for (int i = numberOfItems; i > 0  &&  result > 0; i--) {

      if (result != matrix[i-1][droneWeightLimit]) {
        medicationItems.add(medicationList.get(i-1));
        // we remove items value and weight
        result -= medicationList.get(i-1).getValue();
        droneWeightLimit -= medicationList.get(i-1).getWeight();
      }

    }

    return medicationItems;
  }
}
