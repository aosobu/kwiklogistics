package com.example.houseofprayerlogistics.util;

import java.util.regex.Pattern;

public class ValidatorUtil {

  /**
   * This attempts to generate regex that would enforce a patter
   * say ab_cd or ac-100
   */
  private final String regExp = "^(?=.*[a-z])(?:[a-z0-9]|-(?!-))*(?:[a-z0-9]|_(?!-))*$";
  private final String regExpUpperAlphaNumericSequence = "^(?=.*[a-z])(?:[a-z0-9]|-(?!-))*(?:[a-z0-9]|_(?!-))*$";

  /**
   * This would take care of the basic requirement but would allow names such -- and ----
   *
   */
  private final String regExpTwo = "^[a-zA-Z0-9-_]+$";
  private final String regExpThree = "^[A-Z0-9_]+$";

  /**
   * Likely to allow some invalid characters
   * @param string
   * @return
   */
  public boolean basicNameCheckerForAllCaseCombo(String string){
    return Pattern.matches(regExpTwo, string);
  }

  public boolean basicNameCheckerForUpperCaseCombo(String string){
    return Pattern.matches(regExpThree, string);
  }

  public boolean advancedNameCheckerForAllCaseCombo(String string){
    return Pattern.matches(regExp, string);
  }

  public boolean advancedNameCheckerForUpperCaseCombo(String string){
    return Pattern.matches(regExpUpperAlphaNumericSequence, string);
  }
}
