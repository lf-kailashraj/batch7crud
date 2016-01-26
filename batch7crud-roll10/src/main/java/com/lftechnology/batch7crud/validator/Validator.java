package com.lftechnology.batch7crud.validator;


import java.util.Map;

/**
 * @Author Binod Shrestha <binodshrestha@lftechnology.com>
 * Created on 1/25/16
 */
@FunctionalInterface
public interface Validator<T> {
  Map<String, String> validate(T entity);
}
