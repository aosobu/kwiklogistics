package com.example.houseofprayerlogistics.entity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MedicationEntityTest {

  @Test
  void testDifferenceInLocalDateTimeIntervals(){
    LocalDateTime fromDateTime = LocalDateTime.of(2022, 11, 9, 0, 0, 00);
    LocalDateTime toDateTime = LocalDateTime.of(2022, 11, 9, 3, 0, 00);
    Assertions.assertEquals(3, ChronoUnit.HOURS.between(fromDateTime, toDateTime));
  }

  @Test
  void testCastDifferenceInLocalDateTimeIntervalsLongToShortResultsInSameValue(){
    LocalDateTime fromDateTime = LocalDateTime.of(2022, 11, 8, 0, 0, 00);
    LocalDateTime toDateTime = LocalDateTime.of(2022, 11, 9, 3, 0, 00);
    long result = ChronoUnit.HOURS.between(fromDateTime, toDateTime);
    short age = result <= 32767 ? (short) result : 0;
    Assertions.assertEquals(result, age);
  }
}
