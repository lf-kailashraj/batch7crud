package com.lftechnology.batch7crud.exception;

/**
 * Created by Romit Amgai <romitamgai@lftechnology.com> on 1/28/16.
 */
public class ExceptionHandler extends Exception {
  private ExceptionHandler() {

  }

  public String handle(Exception ex) {
    return ex.getMessage();
  }
}
