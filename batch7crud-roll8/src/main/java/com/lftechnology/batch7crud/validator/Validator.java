package com.lftechnology.batch7crud.validator;

import com.lftechnology.batch7crud.exception.ValidationException;

/**
 * Created by Grishma Shrestha grishmashrestha@lftechnology.com on 1/25/16.
 */
public interface Validator <T> {
  void validate (T entity) throws ValidationException;
}
