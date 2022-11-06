package com.example.houseofprayerlogistics.entity;

import com.example.houseofprayerlogistics.entity.auditable.AbstractAuditable;
import com.example.houseofprayerlogistics.enums.Model;
import com.example.houseofprayerlogistics.enums.State;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Table(name = "DRONE_TB")
public class Drone extends AbstractAuditable {

  private short weight;

  private byte batteryCapacity;

  @Column(length = 100, unique = true)
  private String serialNumber;

  @Enumerated(EnumType.STRING)
  private Model model;

  @Enumerated(EnumType.STRING)
  private State state = State.IDLE;
}
