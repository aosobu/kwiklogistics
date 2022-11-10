package com.example.houseofprayerlogistics.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class DroneServiceIntegrationTest {

  @Autowired
  private DroneService droneService;

  @Test
  public void testLoadDroneOnMainThread(){
    String serialNumber = "XVH5547674734UTPO";
    droneService.loadDrone(serialNumber);
  }

  @Test
  public void testLoadDroneOnMultipleThreadsForOptimisticLockingBehaviour(){
    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("thread one running");
        try {
          String serialNumber = "XVH5547674734UTPO";
          droneService.loadDrone(serialNumber);
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    Thread t2 = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("thread two running");
        String serialNumber = "XVH5547674736UTPO";
        droneService.loadDrone(serialNumber);
      }
    });

    t1.start();
    t2.start();
  }
}

