package com.lftechnology.batch7crud.util;


public class StringUtil {
  private StringUtil() {

  }

  public static boolean isEmptyOrNull(String input) {
    if (input == null || input.isEmpty()) { // NOSONAR
      return true;
    } else {
      return false;
    }
  }
}
