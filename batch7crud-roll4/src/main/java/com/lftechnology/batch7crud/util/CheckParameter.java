package com.lftechnology.batch7crud.util;

/**
 * Created by pratishshr on 1/28/16.
 */
public class CheckParameter {

  private CheckParameter() {

  }

  public static boolean isInt(String value) {
    String intPattern = "[0-9]*";

    return value.matches(intPattern);
  }
}
