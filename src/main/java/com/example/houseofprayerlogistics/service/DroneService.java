package com.example.houseofprayerlogistics.service;

import com.example.houseofprayerlogistics.dto.DroneDTO;
import com.example.houseofprayerlogistics.entity.Drone;
import com.example.houseofprayerlogistics.repository.DroneRepository;
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


  public DroneDTO createDrone(DroneDTO droneDTO){
    Drone drone = modelMapper.map(droneDTO, Drone.class);
    drone = droneRepository.save(drone);
    return modelMapper.map(drone, DroneDTO.class);
  }

}
