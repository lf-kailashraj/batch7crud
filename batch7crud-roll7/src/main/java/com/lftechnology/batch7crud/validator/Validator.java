package com.lftechnology.batch7crud.validator;

import com.lftechnology.batch7crud.exception.ValidatorException;

import java.util.Map;

/**
 * Created by Prajjwal Raj Kandel <prajjwalkandel@lftechnology.com> on 1/25/16.
 */
public interface Validator <T>{
  public T createObject(Map input) throws ValidatorException;

  public Map<String,String> validate(T entity);

}
