package com.example.houseofprayerlogistics.entity.auditable;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Persistable;

@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public abstract class AbstractPersistable<PK extends Serializable> implements Serializable, Persistable<PK> {

  private static final long serialVersionUID = -5554308939380869754L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private PK id;

  public PK getId() {
    return id;
  }

  protected void setId(final PK id) {
    this.id = id;
  }

  @Transient
  public boolean isNew() {
    return null == getId();
  }
}
