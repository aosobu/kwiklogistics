package com.example.houseofprayerlogistics.data;

import com.example.houseofprayerlogistics.entity.Medication;
import com.example.houseofprayerlogistics.entity.MedicationOrder;
import com.example.houseofprayerlogistics.enums.State;
import java.util.ArrayList;
import java.util.List;

public interface TestData {

  static List<MedicationOrder> getMedicationOrderDataSetForLoadItemsWithinDifferentAgeBracket(){
    List<MedicationOrder> medicationOrderList = new ArrayList<>();
    //medications
    Medication medicationOne = Medication.builder().id(1L)
                                                    .weight((short) 100)
                                                    .name("augmentin-100")
                                                    .code("aug-100")
                                                    .profit((short) 100)
                                                    .build();
    Medication medicationTwo = Medication.builder().id(1L)
                                          .weight((short) 200)
                                          .name("baux-200")
                                          .code("baux-200")
                                          .profit((short) 200)
                                          .build();
    Medication medicationThree = Medication.builder().id(1L)
                                        .weight((short) 300)
                                        .name("calf-300")
                                        .code("calf-300")
                                        .profit((short) 300)
                                        .build();
    Medication medicationFour = Medication.builder().id(1L)
                                        .weight((short) 400)
                                        .name("flap-400")
                                        .code("flap-100")
                                        .profit((short) 400)
                                        .build();

    MedicationOrder medicationOrderOne = MedicationOrder.builder()
                                                        .medication(medicationOne)
                                                        .id(1L)
                                                        .age((short) 5)
                                                        .droneId(0L)
                                                        .state(State.IDLE)
                                                        .build();
    MedicationOrder medicationOrderTwo = MedicationOrder.builder()
                                                        .medication(medicationTwo)
                                                        .id(2L)
                                                        .age((short) 4)
                                                        .droneId(0L)
                                                        .state(State.IDLE)
                                                        .build();
    MedicationOrder medicationOrderThree = MedicationOrder.builder()
                                                          .medication(medicationThree)
                                                          .id(3L)
                                                          .age((short) 9)
                                                          .droneId(0L)
                                                          .state(State.IDLE)
                                                          .build();
    MedicationOrder medicationOrderFour = MedicationOrder.builder()
                                                          .medication(medicationFour)
                                                          .id(4L)
                                                          .age((short) 4)
                                                          .droneId(0L)
                                                          .state(State.IDLE)
                                                          .build();
    MedicationOrder medicationOrderFive = MedicationOrder.builder()
        .medication(medicationFour)
        .id(5L)
        .age((short) 1)
        .droneId(0L)
        .state(State.IDLE)
        .build();
    MedicationOrder medicationOrderSix = MedicationOrder.builder()
        .medication(medicationFour)
        .id(6L)
        .age((short) 8)
        .droneId(0L)
        .state(State.IDLE)
        .build();
    MedicationOrder medicationOrderSeven = MedicationOrder.builder()
        .medication(medicationFour)
        .id(7L)
        .age((short) 9)
        .droneId(0L)
        .state(State.IDLE)
        .build();

    medicationOrderList.add(medicationOrderOne);
    medicationOrderList.add(medicationOrderTwo);
    medicationOrderList.add(medicationOrderThree);
    medicationOrderList.add(medicationOrderFour);
    medicationOrderList.add(medicationOrderFive);
    medicationOrderList.add(medicationOrderSix);
    medicationOrderList.add(medicationOrderSeven);

    return medicationOrderList;
  }

  static List<Medication> getMedicationDataSetForLoadItemsWithinDifferentAgeBracket(){
    List<Medication> medicationList = new ArrayList<>();

    //medications
    Medication medicationOne = Medication.builder().id(1L)
        .weight((short) 100)
        .name("augmentin-100")
        .code("aug-100")
        .profit((short) 100)
        .build();
    Medication medicationTwo = Medication.builder().id(1L)
        .weight((short) 200)
        .name("baux-200")
        .code("baux-200")
        .profit((short) 200)
        .build();
    Medication medicationThree = Medication.builder().id(1L)
        .weight((short) 300)
        .name("calf-300")
        .code("calf-300")
        .profit((short) 300)
        .build();
    Medication medicationFour = Medication.builder().id(1L)
        .weight((short) 400)
        .name("flap-400")
        .code("flap-100")
        .profit((short) 400)
        .build();

    medicationList.add(medicationOne);
    medicationList.add(medicationTwo);
    medicationList.add(medicationThree);
    medicationList.add(medicationFour);

    return medicationList;
  }

  static List<MedicationOrder> getMedicationOrderDataSetForLoadItemsWithinSameAgeBracket(){
    List<MedicationOrder> medicationOrderList = new ArrayList<>();
    //medications
    Medication medicationOne = Medication.builder().id(1L)
        .weight((short) 100)
        .name("augmentin-100")
        .code("aug-100")
        .profit((short) 100)
        .build();
    Medication medicationTwo = Medication.builder().id(1L)
        .weight((short) 200)
        .name("baux-200")
        .code("baux-200")
        .profit((short) 200)
        .build();
    Medication medicationThree = Medication.builder().id(1L)
        .weight((short) 300)
        .name("calf-300")
        .code("calf-300")
        .profit((short) 300)
        .build();
    Medication medicationFour = Medication.builder().id(1L)
        .weight((short) 400)
        .name("flap-400")
        .code("flap-100")
        .profit((short) 400)
        .build();

    MedicationOrder medicationOrderOne = MedicationOrder.builder()
        .medication(medicationOne)
        .id(1L)
        .age((short) 5)
        .droneId(0L)
        .state(State.IDLE)
        .build();
    MedicationOrder medicationOrderTwo = MedicationOrder.builder()
        .medication(medicationTwo)
        .id(2L)
        .age((short) 5)
        .droneId(0L)
        .state(State.IDLE)
        .build();
    MedicationOrder medicationOrderThree = MedicationOrder.builder()
        .medication(medicationThree)
        .id(3L)
        .age((short) 5)
        .droneId(0L)
        .state(State.IDLE)
        .build();
    MedicationOrder medicationOrderFour = MedicationOrder.builder()
        .medication(medicationFour)
        .id(4L)
        .age((short) 5)
        .droneId(0L)
        .state(State.IDLE)
        .build();
    MedicationOrder medicationOrderFive = MedicationOrder.builder()
        .medication(medicationFour)
        .id(5L)
        .age((short) 5)
        .droneId(0L)
        .state(State.IDLE)
        .build();
    MedicationOrder medicationOrderSix = MedicationOrder.builder()
        .medication(medicationFour)
        .id(6L)
        .age((short) 5)
        .droneId(0L)
        .state(State.IDLE)
        .build();
    MedicationOrder medicationOrderSeven = MedicationOrder.builder()
        .medication(medicationFour)
        .id(7L)
        .age((short) 5)
        .droneId(0L)
        .state(State.IDLE)
        .build();

    medicationOrderList.add(medicationOrderOne);
    medicationOrderList.add(medicationOrderTwo);
    medicationOrderList.add(medicationOrderThree);
    medicationOrderList.add(medicationOrderFour);
    medicationOrderList.add(medicationOrderFive);
    medicationOrderList.add(medicationOrderSix);
    medicationOrderList.add(medicationOrderSeven);

    return medicationOrderList;
  }
}

