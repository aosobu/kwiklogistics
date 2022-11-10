package com.example.houseofprayerlogistics.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@ConfigurationProperties
@Component
public class KwikLogisticProperties {

  @Data
  @ConfigurationProperties("battery")
  @Component
  public class BatteryProperties {
    private int minCapacity;
  }
}
