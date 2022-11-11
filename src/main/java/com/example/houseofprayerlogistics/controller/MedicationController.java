package com.example.houseofprayerlogistics.controller;

import com.example.houseofprayerlogistics.constants.AppConstants;
import com.example.houseofprayerlogistics.domain.BaseResponse;
import com.example.houseofprayerlogistics.dto.MedicationDTO;
import com.example.houseofprayerlogistics.service.MedicationService;
import com.example.houseofprayerlogistics.util.ValidatorUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
public class MedicationController {

  private final MedicationService service;
  private final ObjectMapper mapper;

  @Autowired
  public MedicationController(
      MedicationService service, ObjectMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  @PostMapping(value = AppConstants.MEDICATION_REGISTRATION, consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
              produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BaseResponse> registerMedication(@RequestParam(value = "image") MultipartFile file,
                                      @RequestParam(value = "jsondata") String medication) throws IOException {

    MedicationDTO medicationDTO = mapper.readValue(medication, MedicationDTO.class);
    medicationDTO.setImage(file);
    medicationDTO.setProfit(medicationDTO.getWeight());

    if(!medicationDTO.validateCode(medicationDTO.getCode()))
      return ResponseEntity.status(HttpStatus.OK)
          .body(new BaseResponse("only upper case letters, underscore and numbers  allowed for code"));

    if(!medicationDTO.validateName(medicationDTO.getName()))
      return ResponseEntity.status(HttpStatus.OK)
          .body(new BaseResponse("only letters, numbers, underscore and dash for name"));

    if(service.registerMedication(medicationDTO)){
      return ResponseEntity.status(HttpStatus.OK)
          .body(new BaseResponse("medication registered successfully"));
    }
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new BaseResponse("medication could not be registed. Contact Administrator"));
  }
}
