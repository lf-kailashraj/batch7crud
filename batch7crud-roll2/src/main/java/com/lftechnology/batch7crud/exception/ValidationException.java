package com.lftechnology.batch7crud.exception;

import java.util.Map;

/**
 * Created by Romit Amgai <romitamgai@lftechnology.com> on 1/25/16.
 */
public class ValidationException extends Exception {
  private Map<String, String> errors; //NOSONAR

  public ValidationException(Map<String, String> errors) {
    this.errors = errors;
  }

  public Map<String, String> getErrors() {
    return this.errors;
  }
}
