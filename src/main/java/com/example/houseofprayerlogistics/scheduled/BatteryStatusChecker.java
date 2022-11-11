package com.example.houseofprayerlogistics.scheduled;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@Slf4j
public class BatteryStatusChecker {

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Scheduled(fixedRate = 120000)
  public void scheduleFixedDelayTask() {
    String sql = "INSERT INTO Battery_History (battery_capacity, created_by, drone_id, created_date, last_modified_date)"
        + " SELECT battery_capacity, 'System',  id, NOW(), NOW()   FROM DRONE_TB";
    int res = jdbcTemplate.update(sql);
    log.info("battery history table populated at {} with {} updates done", LocalDateTime.now(), res);
  }

}
