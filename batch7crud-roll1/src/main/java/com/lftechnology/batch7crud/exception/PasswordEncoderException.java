package com.lftechnology.batch7crud.exception;

/**
 * DataException handles exception related to password hashing process
 *
 * @Author Kiran Pariyar <kiranpariyar@lftechnology.com>
 * Created on 1/29/16.
 */

public class PasswordEncoderException extends Exception {

  public PasswordEncoderException(String message) {
    super(message);
  }
}
