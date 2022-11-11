package com.example.houseofprayerlogistics.entity;

import com.example.houseofprayerlogistics.entity.auditable.AbstractAuditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Table(name = "BATTERY_HISTORY")
public class BatteryHistory extends AbstractAuditable {
  private byte batteryCapacity;
  private String createdBy;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "drone_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private Drone droneId;
}
