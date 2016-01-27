package com.lftechnology.batch7crud.validator;

import java.util.Map;

import com.lftechnology.batch7crud.exception.ValidationException;

public interface Validator<T> {
  public T createObject(Map inputs) throws ValidationException;

  public boolean isValid(T entity) throws ValidationException;
}