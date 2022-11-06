package com.example.houseofprayerlogistics.controller;

import com.example.houseofprayerlogistics.constants.AppConstants;
import com.example.houseofprayerlogistics.dto.DroneDTO;
import com.example.houseofprayerlogistics.service.DroneService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DroneController {

  private final DroneService droneService;

  @Autowired
  public DroneController(
      DroneService droneService) {
    this.droneService = droneService;
  }

  @PostMapping(value = AppConstants.DRONE_REGISTRATION)
  public ResponseEntity<DroneDTO> registerDrone(@RequestBody @Valid DroneDTO droneDTO){
    DroneDTO drone = droneService.createDrone(droneDTO);
    return ResponseEntity.ok(drone);
  }
}
