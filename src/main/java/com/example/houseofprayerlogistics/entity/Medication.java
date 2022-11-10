package com.example.houseofprayerlogistics.entity;

import com.example.houseofprayerlogistics.entity.auditable.AbstractAuditable;
import javax.persistence.Entity;
import javax.persistence.Index;
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

  private short profit;

  @Transient
  private Long medicationOrderId;

//  @Transient
//  private int medicationOrderVersion;

  @Transient
  private int medicationOrderAge;

  @Override
  public int compareTo(Medication medication) {
    if (medication.weight == weight) {
      return 0;
    }else if (medication.weight > weight) {
      return 1;
    } else {
      return -1;
    }
  }
}
