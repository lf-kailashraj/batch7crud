package com.lftechnology.batch7crud.util;

public class NumberUtils {
  private NumberUtils(){
    
  }
  public static boolean isNumeric(String str)  
  {  
    try  
    {  
      Double.parseDouble(str);  
    }  
    catch(NumberFormatException e)  
    {  
      return false;  
    }  
    return true;  
  }
}
