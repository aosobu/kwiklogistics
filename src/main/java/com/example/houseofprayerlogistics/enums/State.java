package com.example.houseofprayerlogistics.enums;

public enum State {
  IDLE("IDLE"),
  LOADING("LOADING"),
  LOADED("LOADED"),
  DELIVERING("DELIVERING"),
  DELIVERED("DELIVERED"),
  RETURNING("RETURNING");

  private String state;

  State(String state) {
    this.state = state;
  }

  public String getState() {
    return state;
  }
}
