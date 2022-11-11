package com.example.houseofprayerlogistics.dto;

import com.example.houseofprayerlogistics.util.ValidatorUtil;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MedicationDTO {

  private String name;
  private short weight;
  private String code;
  private short profit;
  private MultipartFile image;

  private ValidatorUtil validatorUtil;

  public MedicationDTO() {
    this.validatorUtil = new ValidatorUtil();
  }

  public boolean validateName(String name){
    return validatorUtil.basicNameCheckerForAllCaseCombo(name);
  }

  public boolean validateCode(String code){
    return validatorUtil.basicNameCheckerForUpperCaseCombo(code);
  }
}
