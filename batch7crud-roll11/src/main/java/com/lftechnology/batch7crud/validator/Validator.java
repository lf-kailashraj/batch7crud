package com.lftechnology.batch7crud.validator;


import com.lftechnology.batch7crud.exception.ValidationException;

import java.util.Map;

/**
 * Created by achyut on 1/29/16.
 */
public interface Validator<T> {
  void validateEmpty(Map<String, String> inputs) throws ValidationException;

  void validateBusinessLogic(T entity) throws ValidationException;
}
