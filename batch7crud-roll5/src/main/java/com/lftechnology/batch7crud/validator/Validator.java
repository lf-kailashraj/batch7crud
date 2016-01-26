package com.lftechnology.batch7crud.validator;

import java.util.Map;

import com.lftechnology.batch7crud.exception.ValidationExceptions;

public interface Validator<T> {
    public T createObject(Map<String, String> input);

    boolean isValid(T entity) throws ValidationExceptions;
}
