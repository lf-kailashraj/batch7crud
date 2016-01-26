package com.lftechnology.batch7crud.validator;

import java.util.Map;

/**
 * Created by Grishma Shrestha grishmashrestha@lftechnology.com on 1/25/16.
 */
public interface Validator <T> {
  public Map<String, String> validate (T entity);
}
