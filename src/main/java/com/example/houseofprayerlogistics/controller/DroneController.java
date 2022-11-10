package com.example.houseofprayerlogistics.controller;

import com.example.houseofprayerlogistics.constants.AppConstants;
import com.example.houseofprayerlogistics.domain.BaseResponse;
import com.example.houseofprayerlogistics.domain.CustomResponse;
import com.example.houseofprayerlogistics.dto.DroneDTO;
import com.example.houseofprayerlogistics.dto.LoadDroneDTO;
import com.example.houseofprayerlogistics.entity.Drone;
import com.example.houseofprayerlogistics.entity.Medication;
import com.example.houseofprayerlogistics.service.DroneService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
  public ResponseEntity<BaseResponse> registerDrone(@RequestBody @Valid DroneDTO droneDTO){
    if(droneService.createDrone(droneDTO)) {
      return ResponseEntity.ok(
          new BaseResponse(String.format("drone with serial number %s has been successfully registered",
              droneDTO.getSerialNumber())));
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new BaseResponse(String.format("drone with serial number %s already exists",
            droneDTO.getSerialNumber())));
  }

  @PostMapping(value = AppConstants.LOAD_DRONE)
  public ResponseEntity<BaseResponse> loadDrone(@RequestBody @Valid LoadDroneDTO loadDroneDTO){
    if(droneService.loadDrone(loadDroneDTO.getSerialNumber())){
      return ResponseEntity.ok(new BaseResponse("drone loaded successfully"));
    }
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new BaseResponse("drone could not be loaded. Contact Administrator"));
  }

  @GetMapping(value = AppConstants.LOADED_ITEMS)
  public ResponseEntity<CustomResponse<Medication>> getLoadedMedicationItems(@RequestBody @Valid LoadDroneDTO loadDroneDTO){
    return ResponseEntity.ok(new CustomResponse<>(droneService.getLoadedItems(loadDroneDTO.getSerialNumber())));
  }

  @GetMapping(value = AppConstants.AVAILABLE_DRONE)
  public ResponseEntity<CustomResponse<Drone>> checkAvailableDrones(){
    return ResponseEntity.ok(new CustomResponse<>(droneService.getAvailableDrones()));
  }

}
