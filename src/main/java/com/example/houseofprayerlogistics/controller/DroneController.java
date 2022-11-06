package com.example.houseofprayerlogistics.controller;

import com.example.houseofprayerlogistics.constants.AppConstants;
import com.example.houseofprayerlogistics.domain.ApiError;
import com.example.houseofprayerlogistics.dto.DroneDTO;
import com.example.houseofprayerlogistics.service.DroneService;
import java.util.Collections;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
  public ResponseEntity<Object> registerDrone(@RequestBody @Valid DroneDTO droneDTO){
    if(droneService.createDrone(droneDTO)) {
      return ResponseEntity.ok(String.format(AppConstants.DRONE_SUCCESSFUL_REGISTER, droneDTO.getSerialNumber()));
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new ApiError(Collections.singletonList(String.format(AppConstants.DRONE_FAILURE_REGISTER, droneDTO.getSerialNumber()))));
  }
}
