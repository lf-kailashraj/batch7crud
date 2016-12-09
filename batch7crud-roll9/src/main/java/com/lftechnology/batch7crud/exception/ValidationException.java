package com.lftechnology.batch7crud.exception;

/**
 * @Author Sanjay Maharjan <sanjaymaharjan@lftechnology.com>
 * Created on 1/25/16
 */

import java.util.HashMap;
import java.util.Map;

public class ValidationException extends Exception {
  private Map<String, String> errors = new HashMap<>();  //NOSONAR

  public ValidationException() {
    super("validation failed");
  }

  public Map<String, String> getErrors() {
    return errors;
  }

  public void setErrors(Map<String, String> errors) {
    this.errors = errors;
  }
}
