package com.lftechnology.batch7crud.exception;

import java.util.Map;

public class ValidationExceptions extends Exception {
  Map<String, String> errors;

  public ValidationExceptions() {
    super("Validation error");
  }

  public ValidationExceptions(String message) {
    super(message);
  }

  public Map<String, String> getErrors() {
    return errors;
  }

  public void setErrors(Map<String, String> errors) {
    this.errors = errors;
  }
}
