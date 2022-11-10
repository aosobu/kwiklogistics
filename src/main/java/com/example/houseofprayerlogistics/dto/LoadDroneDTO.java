package com.example.houseofprayerlogistics.dto;

import javax.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoadDroneDTO {
  @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "can only contain numbers and digits")
  private String serialNumber;
}
