package com.lftechnology.batch7crud.validator;


import com.lftechnology.batch7crud.exception.ValidationException;


/**
 * @Author Binod Shrestha <binodshrestha@lftechnology.com>
 * Created on 1/25/16
 */
@FunctionalInterface
public interface Validator<T> {
  void validate(T entity) throws ValidationException;
}
