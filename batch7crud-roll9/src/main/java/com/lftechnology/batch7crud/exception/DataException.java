package com.lftechnology.batch7crud.exception;

/**
 * Created by sanjay on 1/18/16.
 */
public class DataException extends Exception {
  public DataException() {
    super("Error while database opetaions.");
  }

  public DataException(String message) {
    super(message);
  }
}
