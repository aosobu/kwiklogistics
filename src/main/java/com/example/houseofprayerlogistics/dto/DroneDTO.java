package com.example.houseofprayerlogistics.dto;

import com.example.houseofprayerlogistics.constants.AppConstants;
import com.example.houseofprayerlogistics.enums.Model;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class DroneDTO {
  private Long id;

  private Model model;

  @NotBlank(message = AppConstants.SERIALNUMBER_VALIDATION_MESSAGE)
  @Size(max = 100, message = AppConstants.SERIALNUMBER_MAX_VALIDATION_MESSAGE)
  private String serialNumber;

  @Min(value = 50, message = AppConstants.WEIGHT_MIN_VALIDATION_MESSAGE)
  @Max(value = 500, message = AppConstants.WEIGHT_VALIDATION_MESSAGE)
  private short weight;


  @Min(value = 1, message = AppConstants.BATTERY_MIN_VALIDATION_MESSAGE)
  @Max(value = 100, message = AppConstants.BATTERY_VALIDATION_MESSAGE)
  private byte batteryCapacity;
}
