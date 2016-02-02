package com.lftechnology.batch7crud.util;

/**
 * Created by achyut on 1/29/16.
 */
public class NumberUtil {
  private NumberUtil(){}

  public static boolean isNumeric(String input){
    try{
      Double.parseDouble(input);
    }catch (NumberFormatException e){
      return false;
    }
    return true;
  }

}
