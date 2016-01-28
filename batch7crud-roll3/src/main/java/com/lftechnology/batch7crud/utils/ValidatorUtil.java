package com.lftechnology.batch7crud.utils;

public abstract class ValidatorUtil {
  protected boolean isEmpty(String value) {
    if ("".equals(value.trim())) {
      return true;
    }
    return false;
  }

  protected boolean isString(String value) {
    String onlyString = "[a-zA-Z ]*";

    if (value.matches(onlyString)) {
      return true;
    }
    return false;
  }

  protected boolean isInteger(String value) {
    String onlyInteger = "[0-9]*";

    if (value.matches(onlyInteger)) {
      return true;
    }
    return false;
  }

}
