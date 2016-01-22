package com.lftechnology.batch7crud.exception;

/**
 * Created by leapfrog on 1/18/16.
 */
public class DataException extends Exception {
  public DataException() {
    super("Exception from Database");
  }

  public DataException(String message) {
    super(message);
  }
}
