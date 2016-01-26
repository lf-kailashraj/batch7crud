package com.lftechnology.batch7crud.validator;

import com.lftechnology.batch7crud.exception.ValidationException;

/**
 * Created by pratishshr on 1/25/16.
 */
public interface Validator<T> {
  public void validate(T entity) throws ValidationException;
}
