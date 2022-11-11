package com.example.houseofprayerlogistics;

import com.example.houseofprayerlogistics.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableJpaAuditing
@SpringBootApplication
public class HouseOfPrayerLogisticsApplication implements CommandLineRunner {

  @Autowired
  private DroneService droneService;

  public static void main(String[] args) {
    SpringApplication.run(HouseOfPrayerLogisticsApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          String serialNumber = "XVH5547674734UTPO";
          droneService.loadDrone(serialNumber);
          System.out.println("drone with id " + serialNumber + " has been loaded");
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
    t1.start();
  }
}
