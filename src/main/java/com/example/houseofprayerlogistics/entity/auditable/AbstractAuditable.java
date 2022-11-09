package com.example.houseofprayerlogistics.entity.auditable;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditable extends AbstractPersistable<Long> {

  @Column(updatable = false)
  @CreatedDate
  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
  private LocalDateTime createdDate;

  @Column
  @LastModifiedDate
  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
  private LocalDateTime lastModifiedDate;
}
