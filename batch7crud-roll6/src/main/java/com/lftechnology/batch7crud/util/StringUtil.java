package com.lftechnology.batch7crud.util;

public class StringUtil {
  public boolean isEmptyOrNull(String input) {
    if (input == null || input.isEmpty()) {
      return true;
    }
    return false;
  }
}
