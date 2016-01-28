package com.lftechnology.batch7crud.validator;

import java.util.Map;

/**
 * Created by Romit Amgai <romitamgai@lftechnology.com> on 1/25/16.
 */
public interface GenericValidator<T> {

  T createObject(Map<String, String> input);

  Map<String, String> validate(T entity);
}
