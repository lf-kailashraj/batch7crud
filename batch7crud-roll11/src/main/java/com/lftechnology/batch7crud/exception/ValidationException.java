package com.lftechnology.batch7crud.exception;

import java.util.Map;

/**
 * Created by achyut on 1/29/16.
 */
public class ValidationException extends Exception {
  private final Map<String, String> errors;

  public ValidationException(Map<String, String> errors){
    this.errors = errors;
  }

  public Map<String, String> getErrors(){
    return errors;
  }
}
