package com.example.houseofprayerlogistics.entity;

import com.example.houseofprayerlogistics.entity.auditable.AbstractAuditable;
import javax.persistence.Entity;
import javax.persistence.Index;
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
@Table(name = "MEDICATION_TB", indexes = @Index(name = "code_index" , columnList = "code"))
public class Medication extends AbstractAuditable {
  private String name;
  private short weight;
  private String code;
  private String imagePath;
}
