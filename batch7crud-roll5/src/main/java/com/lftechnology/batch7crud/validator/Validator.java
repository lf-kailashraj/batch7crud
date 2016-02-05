package com.lftechnology.batch7crud.validator;

import com.lftechnology.batch7crud.exception.ValidationExceptions;

public interface Validator<T> {
    boolean isValid(T entity) throws ValidationExceptions;
}
