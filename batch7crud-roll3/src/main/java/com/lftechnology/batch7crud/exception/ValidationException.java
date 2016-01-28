package com.lftechnology.batch7crud.exception;

import java.util.Map;

public class ValidationException extends Exception {
  private Map<String, String> errors; // NOSONAR

  public ValidationException() {
    super("Validation error");
  }

  public ValidationException(String message) {
    super(message);
  }

  public Map<String, String> getErrors() {
    return errors;
  }

  public void setErrors(Map<String, String> errors) {
    this.errors = errors;
  }
}
