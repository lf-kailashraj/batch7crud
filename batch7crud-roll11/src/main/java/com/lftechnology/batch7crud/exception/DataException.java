package com.lftechnology.batch7crud.exception;

public class DataException extends Exception {

  public DataException() {
    super("Data access exception");
  }

  public DataException(String message) {
    super(message);
  }

}