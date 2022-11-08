package com.example.houseofprayerlogistics.entity;

import com.example.houseofprayerlogistics.entity.auditable.AbstractAuditable;
import javax.persistence.Entity;
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
@Table(name = "DRONE_BATTERY_HISTORY_TB")
public class DroneBatteryHistory extends AbstractAuditable {
  private Long droneId;
  private byte batteryCapacity;
}
