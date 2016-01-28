package com.lftechnology.batch7crud.exception;

/**
 * Created by Romit Amgai <romitamgai@lftechnology.com> on 1/19/16.
 */
public class DataException extends Exception {
  public DataException() {
    super("Errors occurred while taking data");
  }

  public DataException(String message) {
    super(message);
  }
}
