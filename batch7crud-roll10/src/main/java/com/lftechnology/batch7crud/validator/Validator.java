package com.lftechnology.batch7crud.validator;


import java.util.Map;

/**
 * @Author Binod Shrestha <binodshrestha@lftechnology.com>
 * Created on 1/25/16
 */
public interface Validator<T> {

  T createObject(Map<String, String> params, Map<String, String> errors);

  Map<String, String> validate(T entity);
}
