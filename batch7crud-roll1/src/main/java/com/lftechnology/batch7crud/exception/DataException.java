package com.lftechnology.batch7crud.exception;

public class DataException extends Exception {

  public DataException() {
    super("exception while getting data from database");
  }

  public DataException(String message) {
    super(message);
  }

}
