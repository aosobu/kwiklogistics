package com.example.houseofprayerlogistics.entity;

import com.example.houseofprayerlogistics.entity.auditable.AbstractAuditable;
import com.example.houseofprayerlogistics.enums.State;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
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
@Table(name = "MEDICATION_ORDER_TB")
public class MedicationOrder extends AbstractAuditable {

  private int droneId;

  @Version
  private long version;

  @Enumerated(EnumType.STRING)
  private State state = State.IDLE;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "medication_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private Medication medication;
}
