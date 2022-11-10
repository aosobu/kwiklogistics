package com.example.houseofprayerlogistics.entity;

import com.example.houseofprayerlogistics.entity.auditable.AbstractAuditable;
import com.example.houseofprayerlogistics.enums.State;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;
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
public class MedicationOrder extends AbstractAuditable  implements Comparable<MedicationOrder> {

  private Long droneId;

  @Transient
  private short age;

  @Enumerated(EnumType.STRING)
  private State state = State.IDLE;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "medication_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private Medication medication;


  @PostLoad
  public void setValue(){
    this.age = (short) ChronoUnit.HOURS.between(this.getCreatedDate(), LocalDateTime.now());
    this.medication.setMedicationOrderId(this.getId());
    this.medication.setMedicationOrderAge(this.age);
  }

  @Override
  public int compareTo(MedicationOrder medicationOrder) {
    if (medicationOrder.age == age) {
      return 0;
    }else if (medicationOrder.age > age) {
      return 1;
    } else {
      return -1;
    }
  }
}
