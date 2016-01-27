package com.lftechnology.batch7crud.validator;

import com.lftechnology.batch7crud.exception.ValidationException;

import java.util.Map;

/**
 * Interface for entity object validation
 *
 * @Author Kiran Pariyar <kiranpariyar@lftechnology.com>
 * Created on 1/25/16.
 */
public interface Validator<T> {

  void validate(T entity) throws ValidationException;
}
