package com.example.houseofprayerlogistics.controller;

import com.example.houseofprayerlogistics.constants.AppConstants;
import com.example.houseofprayerlogistics.dto.DroneDTO;
import com.example.houseofprayerlogistics.service.DroneService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({MethodArgumentNotValidException.class})
  public Map<String, String> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return errors;
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({HttpMessageNotReadableException.class})
  public List<String> handleHttpMessageNotReadableException(
      HttpMessageNotReadableException exp) {
    List<String> errors = new ArrayList<>();
    errors.add(exp.getCause().getLocalizedMessage());
    return errors;
  }
}
