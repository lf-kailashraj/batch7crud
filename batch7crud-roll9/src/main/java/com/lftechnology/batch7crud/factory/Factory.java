package com.lftechnology.batch7crud.factory;

import com.lftechnology.batch7crud.exception.ValidationException;

import java.util.Map;

/**
 * Created by sanjay on 1/28/16.
 */
public interface Factory<T> {
  public T createObject(Map<String, String> input) throws ValidationException;
}