package com.lftechnology.batch7crud.exception;

/**
 * @Author Binod Shrestha <binodshrestha@lftechnology.com>
 * Created on 1/18/16
 */
public class DataException extends Exception {

  public DataException() {
    super("Data exception");
  }

  public DataException(String message) {
    super(message);
  }
}
