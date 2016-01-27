package com.lftechnology.batch7crud.validator;

import com.lftechnology.batch7crud.exception.ValidationException;

/**
 * Created by Romit Amgai <romitamgai@lftechnology.com> on 1/25/16.
 */
public interface GenericValidator<T> {

  void validate(T entity) throws ValidationException;
}
