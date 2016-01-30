package com.lftechnology.batch7crud.exception;

import java.util.Map;

/**
 * ValidationException handles exception related users input data
 *
 * @Author Kiran Pariyar <kiranpariyar@lftechnology.com>
 * Created on 1/25/16.
 */
public class ValidationException extends Exception {

  private final transient Map<String, String> errors;

  public ValidationException(Map<String, String> errors) {
    this.errors = errors;
  }

  public Map<String, String> getErrors() {
    return errors;
  }

}
