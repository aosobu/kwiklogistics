package com.example.houseofprayerlogistics.Util;

import com.example.houseofprayerlogistics.util.ValidatorUtil;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.h2.table.FunctionTable;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class util {

  ValidatorUtil validatorUtil;
  private String letter;

  @Before
  public void init(){
    validatorUtil = new ValidatorUtil();
  }

  @Test
  public void validateNameCollection(){
    validatorUtil = new ValidatorUtil();
    String name = "funx-800";
    String nameOne = "combatrin-200g-roaster";
    String nameThree = "combatrin_200g";
    String nameFour = "roaster87X_YUT";

    Assertions.assertTrue(validatorUtil.basicNameCheckerForAllCaseCombo(name));
    Assertions.assertTrue(validatorUtil.basicNameCheckerForAllCaseCombo(nameOne));
    Assertions.assertTrue(validatorUtil.basicNameCheckerForAllCaseCombo(nameThree));
    Assertions.assertTrue(validatorUtil.basicNameCheckerForAllCaseCombo(nameFour));
  }

  @Test
  public void validateCodeCollection(){
    validatorUtil = new ValidatorUtil();

    String code = "COMBAT_200";
    String codeTwo = "UNACT_200XV";
    String codeThree = "RELOAD_200XV";

    Assertions.assertTrue(validatorUtil.basicNameCheckerForUpperCaseCombo(code));
    Assertions.assertTrue(validatorUtil.basicNameCheckerForUpperCaseCombo(codeTwo));
    Assertions.assertTrue(validatorUtil.basicNameCheckerForUpperCaseCombo(codeThree));
  }

  @Test
  public void testedNumber(){
    String name = "Count the number of C's in the listing. All c na c";
     HashMap<String, Long> map = (HashMap<String, Long>) Arrays.stream(name.toLowerCase().split(""))
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
     AtomicLong max = new AtomicLong();
    System.out.println(map);

     map.forEach((key, value) -> {
       if(Pattern.matches("^[a-z]$", key)){
         if (max.get() < value) {
           max.set(value);
           letter = key;
         }
       }
     });
  }

}
