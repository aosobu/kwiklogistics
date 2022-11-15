package com.example.houseofprayerlogistics.service;

import com.example.houseofprayerlogistics.config.KwikLogisticProperties.BatteryProperties;
import com.example.houseofprayerlogistics.dto.DroneDTO;
import com.example.houseofprayerlogistics.entity.Drone;
import com.example.houseofprayerlogistics.entity.Medication;
import com.example.houseofprayerlogistics.entity.MedicationOrder;
import com.example.houseofprayerlogistics.enums.State;
import com.example.houseofprayerlogistics.repository.DroneRepository;
import com.example.houseofprayerlogistics.repository.MedicationOrderRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class DroneService {

  private final ModelMapper modelMapper;
  private final DroneRepository droneRepository;
  private final MedicationOrderRepository medicationOrderRepository;
  private final BatteryProperties batteryProperties;
  private short droneWeightLimit;
  private List<Medication> medicationItems;
  private Map<Short, List<MedicationOrder>> ageBasedMedicationOrderMap;
  private Drone droneExists;

  @Autowired
  public DroneService(ModelMapper modelMapper,
      DroneRepository droneRepository,
      MedicationOrderRepository medicationOrderRepository,
      BatteryProperties batteryProperties) {
    this.modelMapper = modelMapper;
    this.droneRepository = droneRepository;
    this.medicationOrderRepository = medicationOrderRepository;
    this.batteryProperties = batteryProperties;
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

  @Transactional
  public boolean loadDrone(String serialNumber){
    List<MedicationOrder> medicationOrderForUpdate = new ArrayList<>();

    if(loadDroneWithItems(serialNumber)){
      medicationItems.forEach(item -> {
        short key = (short) item.getMedicationOrderAge();
        ageBasedMedicationOrderMap.get(key).forEach(medicationOrder ->{
          if(medicationOrder.getId() == item.getMedicationOrderId()){
            medicationOrder.setDroneId(droneExists.getId());
            medicationOrder.setState(State.LOADED);
            medicationOrderForUpdate.add(medicationOrder);
          }
        });
      });

      //perform updates
      medicationOrderRepository.saveAll(medicationOrderForUpdate);
      medicationItems.clear();
      ageBasedMedicationOrderMap.clear();

      droneExists.setState(State.LOADED);
      droneRepository.save(droneExists);
      return true;
    }

    return false;
  }

  public boolean loadDroneWithItems(String serialNumber){
    Optional<Drone> drone = droneRepository.findDroneBySerialNumber(serialNumber);
    if(!drone.isPresent()){
      return false;
    }

    droneExists = drone.get();
    if(!droneExists.getState().equals(State.IDLE)){
      return false;
    }

    if(droneExists.getBatteryCapacity() >=  batteryProperties.getMinCapacity()){
      Optional<List<MedicationOrder>> medicationOrderList = medicationOrderRepository.findAllByState(State.IDLE);

      if(medicationOrderList.isPresent()){
        droneExists.setState(State.LOADING);
        droneRepository.save(droneExists);

        List<MedicationOrder> medicationOrders = medicationOrderList.get();
        ageBasedMedicationOrderMap = generateAgeBasedMedicationOrderMap(medicationOrders);

        //initialize drone weight limits and medication items
        droneWeightLimit = droneExists.getWeightLimit();
        medicationItems = new ArrayList<>();

        ageBasedMedicationOrderMap.forEach((key, value) -> {
          List<Medication> medications = new ArrayList<>();
          value.forEach(medicationOrder -> medications.add(medicationOrder.getMedication()));
          loadDroneWithMedication(medications, droneExists);
        });
      }
      return !medicationItems.isEmpty();
    }

    return false;
  }

  /**
   *
   * This method first generates an aged based classification map of medication order items.
   * This is achieved by determining the age of each medication item.
   *
   * Utilizing the age, a map is created which holds the age as key and the list
   * of items within the same age bracket as value.
   *
   * The age is the difference, in hours, between the created datetime of each item and the time when
   * it is loaded into the application
   *
   * This is to ensure that items are treated in a first-in-first-out basis, i.e. the order
   * in which they were added to the database.
   *
   * Utilizing the derived map, the list is then passed through a knapsack engine
   * to ensure drone capacity is optimally filled with medication items based on a first-in-first-out
   * basis as described above.
   *
   * This is achieved by traversing the map after guaranteeing that the keys are arranged in
   * descending order.
   *
   * Note: Keys represent the time difference in hours from arrival in database to read by the application.
   *
   */
  public Map<Short, List<MedicationOrder>> generateAgeBasedMedicationOrderMap(List<MedicationOrder> medicationOrderList){
    LinkedHashMap<Short, List<MedicationOrder>> ageBasedMedicationOrderMap = new LinkedHashMap<>();
    Collections.sort(medicationOrderList);
    boolean start = true;

    int j = 0;
    while(start){
      List<MedicationOrder> tempList = new ArrayList<>();

      for(int i=j; i<medicationOrderList.size();){

        short key = medicationOrderList.get(i).getAge();
        tempList.add(medicationOrderList.get(i));

        if(ageBasedMedicationOrderMap.containsKey(key)){
          tempList = ageBasedMedicationOrderMap.get(key);
          tempList.add(medicationOrderList.get(i));
          ageBasedMedicationOrderMap.put(key,tempList);
        }else{
          ageBasedMedicationOrderMap.put(key,tempList);
        }
        if(i == medicationOrderList.size() - 1)
          start = false;
        j++;
        break;
      }
    }

    return ageBasedMedicationOrderMap;
  }


  public List<Medication> loadDroneWithMedication(List<Medication> medicationList, Drone drone){
    int[][] possibleMedicationLoadMatrix = generateMedicationLoadMatrix(medicationList, drone);
    medicationItems = getLoadItemsFromMatrix(possibleMedicationLoadMatrix, medicationList.size(), droneWeightLimit, medicationList);

    if (droneWeightLimit == 0) {
      return medicationItems;
    }
    return medicationItems;
  }

  public int[][] generateMedicationLoadMatrix(List<Medication> medicationList, Drone drone) {
    int numberOfItems = medicationList.size();
    int capacity = drone.getWeightLimit();

    int[][] matrix = new int[numberOfItems + 1][capacity + 1];
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
              + medicationList.get(i-1).getProfit());
        }
      }
    }

    return matrix;
  }

  public List<Medication> getLoadItemsFromMatrix(int[][] matrix,
      int numberOfItems,
      short capacity, List<Medication> medicationList){

    int result = matrix[numberOfItems][capacity];
    droneWeightLimit = capacity;

    //select required items via load matrix
    for (int i = numberOfItems; i > 0  &&  result > 0; i--) {
      if (result != matrix[i-1][droneWeightLimit]) {
        medicationItems.add(medicationList.get(i-1));
        // we remove items value and weight
        result -= medicationList.get(i-1).getProfit();
        droneWeightLimit -= medicationList.get(i-1).getWeight();
      }
    }

    return medicationItems;
  }

  public List<Medication> getLoadedItems(String serialNUmber){
    List<Medication> loadedMedicationItems = new ArrayList<>();

    Optional<Drone> drone = droneRepository.findDroneBySerialNumber(serialNUmber);
    if(!drone.isPresent()){
      return new ArrayList<>();
    }

    Optional<List<MedicationOrder>> loadedItems = medicationOrderRepository.findAllByDroneId(drone.get().getId());
    loadedItems.ifPresent(medicationOrders -> medicationOrders.forEach(
        item -> loadedMedicationItems.add(item.getMedication())));

    return loadedMedicationItems;
  }

  public List<Drone> getAvailableDrones(){
    Optional<List<Drone>> availableDrones = droneRepository.findAllByState(State.IDLE);
    return availableDrones.orElseGet(ArrayList::new);
  }

  public int getBatteryLevel(String serialNumber){
    Optional<Drone> drone = droneRepository.findDroneBySerialNumber(serialNumber);
    return drone.map(Drone::getBatteryCapacity).orElse((byte) 0);
  }

}
