package com.example.houseofprayerlogistics.entity;

import com.example.houseofprayerlogistics.entity.auditable.AbstractAuditable;
import com.example.houseofprayerlogistics.enums.State;
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
@Table(name = "DRONE_STATE_HISTORY_TB")
public class DroneStateHistory extends AbstractAuditable {
  private State stateList;
}
