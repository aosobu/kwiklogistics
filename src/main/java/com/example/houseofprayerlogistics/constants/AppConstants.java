package com.example.houseofprayerlogistics.constants;

public class AppConstants {
  //endpoints
  public static final String DRONE_REGISTRATION = "/register/drone";

  //Validation messages
  public static final String WEIGHT_VALIDATION_MESSAGE = "weight cannot exceed 500 grams";
  public static final String WEIGHT_MIN_VALIDATION_MESSAGE = "weight cannot be less than 50g";
  public static final String BATTERY_VALIDATION_MESSAGE = "battery capacity cannot exceed 100%";
  public static final String BATTERY_MIN_VALIDATION_MESSAGE = "battery capacity cannot be lower than 1%";
  public static final String SERIALNUMBER_VALIDATION_MESSAGE = "serial number cannot be empty";
  public static final String SERIALNUMBER_MAX_VALIDATION_MESSAGE = "serial number should not exceed 100 characters";
}
