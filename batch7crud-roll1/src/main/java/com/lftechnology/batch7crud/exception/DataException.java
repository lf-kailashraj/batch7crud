package com.lftechnology.batch7crud.exception;

/**
 * DataException handles exception related to data operation
 *
 * @Author Kiran Pariyar <kiranpariyar@lftechnology.com>
 * Created on 1/18/16
 */

public class DataException extends Exception {

  public DataException() {
    super("exception while getting data from database");
  }

  public DataException(String message) {
    super(message);
  }

}
