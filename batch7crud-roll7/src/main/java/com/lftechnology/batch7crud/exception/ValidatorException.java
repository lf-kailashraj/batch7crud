package com.lftechnology.batch7crud.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Prajjwal Raj Kandel <prajjwalkandel@lftechnology.com> on 1/25/16.
 */
public class ValidatorException extends Exception {
  private Map<String,String> errors = new HashMap<>();

  public ValidatorException(String msg,Map errors)
  {
    super(msg);
    this.errors = errors;
  }

  public Map<String,String> getErrors() {
    return errors;
  }
}
