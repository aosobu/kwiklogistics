package com.example.houseofprayerlogistics.service;

import com.example.houseofprayerlogistics.dto.DroneDTO;
import com.example.houseofprayerlogistics.entity.Drone;
import com.example.houseofprayerlogistics.repository.DroneRepository;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DroneService {

  private ModelMapper modelMapper;
  private DroneRepository droneRepository;

  @Autowired
  public DroneService(ModelMapper modelMapper,
      DroneRepository droneRepository) {
    this.modelMapper = modelMapper;
    this.droneRepository = droneRepository;
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

}
