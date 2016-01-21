package com.lftechnology.batch7crud.exception;

import static com.lftechnology.batch7crud.constant.MessageConstant.*;

/**
 * Created by Pratish Shrestha <pratishshrestha@lftechnology.com> on 1/18/16.
 */
public class DataException extends Exception {
  public DataException() {
    super(MESSAGE_DATA_EXCEPTION);
  }

  public DataException(String message) {
    super(message);
  }
}
