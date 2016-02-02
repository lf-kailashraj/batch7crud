package com.lftechnology.batch7crud.validator;

import java.util.Map;

/**
 * Created by pratishshr on 1/25/16.
 */
public interface Validator<T> {
  public T createObject(Map<String, String> input);

  public Map<String, String> validate(T entity);
}
