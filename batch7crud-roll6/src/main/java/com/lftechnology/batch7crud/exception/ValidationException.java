package com.lftechnology.batch7crud.exception;

import java.util.Map;

/**
 * @author madandhungana <madandhungana@lftechnology.com> Jan 26, 2016 batch7crud-roll6 2016
 */
public class ValidationException extends Exception {
  private static final long serialVersionUID = 1L;
  
  private Map<String, String> errors; // NOSONAR

  public ValidationException(Map<String, String> errors) {
    this.errors = errors;
  }

  public Map<String, String> getErrors() {
    return this.errors;
  }
}
