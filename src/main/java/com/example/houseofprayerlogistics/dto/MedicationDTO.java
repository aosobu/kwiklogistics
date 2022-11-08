package com.example.houseofprayerlogistics.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MedicationDTO {
  private String name;
  private short weight;
  private String code;
  private MultipartFile image;
}
