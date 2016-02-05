package com.lftechnology.batch7crud.validator;

import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.model.User;


public interface Validator<T> {

  public void  validate(T entity) throws ValidationException;

  void validate(User user) throws ValidationException;

}
