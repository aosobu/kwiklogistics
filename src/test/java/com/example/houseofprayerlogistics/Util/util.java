package com.example.houseofprayerlogistics.Util;

import com.example.houseofprayerlogistics.util.ValidatorUtil;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class util {

  ValidatorUtil validatorUtil;

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

}
