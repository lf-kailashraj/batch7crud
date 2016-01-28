package com.lftechnology.batch7crud.utils;

public abstract class ValidatorUtil {
  public static boolean isEmpty(String value) {
    if (value == null || value.trim().isEmpty()) {
      return true;
    }
    return false;
  }

  public static boolean isString(String value) {
    String onlyString = "[a-zA-Z ]*";

    if (value.matches(onlyString)) {
      return true;
    }
    return false;
  }

  public static boolean isInteger(String value) {
    String onlyInteger = "[0-9]*";

    if (value.matches(onlyInteger)) {
      return true;
    }
    return false;
  }

}
