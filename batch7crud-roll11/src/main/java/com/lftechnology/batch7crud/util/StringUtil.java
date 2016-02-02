package com.lftechnology.batch7crud.util;


/**
 * Created by achyut on 1/29/16.
 */
public class StringUtil {
  private StringUtil(){}

  public static boolean isEmpty(String input){
    if(input == null || input.isEmpty()) {
      return true;
    }
    return false;
  }

  public static String[] parseUrl(String inputStr) {
    return inputStr.split("/");
  }

}
