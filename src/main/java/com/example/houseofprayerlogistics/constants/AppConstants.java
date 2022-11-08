package com.example.houseofprayerlogistics.constants;

public class AppConstants {
  //endpoints
  public static final String DRONE_REGISTRATION = "/register/drone";
  public static final String MEDICATION_REGISTRATION = "/register/medication";

  //Validation messages
  public static final String WEIGHT_VALIDATION_MESSAGE = "weight cannot exceed 500 grams";
  public static final String WEIGHT_MIN_VALIDATION_MESSAGE = "weight cannot be less than 50g";
  public static final String BATTERY_VALIDATION_MESSAGE = "battery capacity cannot exceed 100%";
  public static final String BATTERY_MIN_VALIDATION_MESSAGE = "battery capacity cannot be lower than 1%";
  public static final String SERIALNUMBER_VALIDATION_MESSAGE = "serial number cannot be empty";
  public static final String SERIALNUMBER_MAX_VALIDATION_MESSAGE = "serial number should not exceed 100 characters";

  //response messages
  public static final String DRONE_SUCCESSFUL_REGISTER = "drone with serial number %s has been successfully registered";
  public static final String DRONE_FAILURE_REGISTER = "drone with serial number %s already exists";

  //error message when uploading medication
  public static final String DUPLICATE_IMAGE_MESSAGE = "please choose another image as medication with image exists. If you choose to use this image, kindly rename it.";
  public static final String DATABASE_SAVE_MESSAGE = "an error occurred while saving medication record to the database";

  //image file path
  public static final String FOLDER_PATH = "images";
  public static String MESSAGE = "";

  public static final String SUCCESS = "success";
}
