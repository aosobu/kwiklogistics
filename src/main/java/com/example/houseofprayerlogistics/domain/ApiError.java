package com.example.houseofprayerlogistics.domain;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ApiError {

  private List<String> errors;
  private Map<String, String> errorMap;

  public ApiError(List<String> errors) {
    this.errors = errors;
  }

  public ApiError(Map<String, String> errorMap) {
    this.errorMap = errorMap;
  }
}
