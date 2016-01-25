package com.lftechnology.batch7crud.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Binod Shrestha <binodshrestha@lftechnology.com>
 * Created on 1/25/16
 */
public class ValidationException extends Exception{
  private Map<String, String> errors = new HashMap<>();

  public ValidationException(){
    super("validation failed");
  }

  public ValidationException(String message){
    super(message);
  }

  public ValidationException(String message, Map<String, String> errors){
    super(message);
    this.errors = errors;
  }

  public Map<String, String> getErrors(){
    return errors;
  }
}
