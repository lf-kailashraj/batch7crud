package com.lftechnology.batch7crud.exception;

/**
 * Created by Grishma Shrestha grishmashrestha@lftechnology.com on 1/18/16.
 */

public class DataException extends Exception {
  public DataException(){
    super("Error encountered at SQL");
  }

  public DataException(String message){
    super(message);
  }
}
