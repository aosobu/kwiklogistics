package com.example.houseofprayerlogistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HouseOfPrayerLogisticsApplication {
  public static void main(String[] args) {
    SpringApplication.run(HouseOfPrayerLogisticsApplication.class, args);
  }
}
