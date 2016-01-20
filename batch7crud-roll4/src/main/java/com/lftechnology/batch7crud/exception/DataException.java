package com.lftechnology.batch7crud.exception;

/**
 * Created by pratishshr on 1/18/16.
 */
public class DataException extends Exception {
  public DataException() {
    super("Exception while reading data");
  }

  public DataException(String message) {
    super(message);
  }
}
