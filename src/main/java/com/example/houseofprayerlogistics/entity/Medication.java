package com.example.houseofprayerlogistics.entity;

import com.example.houseofprayerlogistics.entity.auditable.AbstractAuditable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;


@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
@Table(name = "MEDICATION_TB", indexes = @Index(name = "code_index" , columnList = "code"))
public class Medication extends AbstractAuditable implements Comparable<Medication> {
  private String name;

  private short weight;

  private String code;

  private String imagePath;

  @Transient
  private short value;

  @Transient
  private short age;

  @PostLoad
  public void setValue(){
    this.value = this.weight;
    this.age = (short) ChronoUnit.HOURS.between(this.getCreatedDate(), LocalDateTime.now());
  }

  @Override
  public int compareTo(Medication medication) {
    if (age == medication.age) {
      return 0;
    }else if (age > medication.age) {
      return 1;
    } else {
      return -1;
    }
  }
}
