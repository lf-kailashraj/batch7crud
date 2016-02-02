package com.lftechnology.batch7crud.validator;

import java.util.Map;


public interface Validator<T> {

  public void  validate(T entity,Map<String, String> errors);

}
