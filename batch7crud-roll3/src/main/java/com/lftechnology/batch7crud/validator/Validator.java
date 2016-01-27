package com.lftechnology.batch7crud.validator;

import java.util.Map;

import com.lftechnology.batch7crud.exception.ValidationException;

public interface Validator<T> {

  public void validateObject(T entity) throws ValidationException;

  public void validateInputs(Map inputs) throws ValidationException;
}