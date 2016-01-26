package com.lftechnology.batch7crud.validator;

import java.util.Map;

/**
 * Interface for entity object validation
 *
 * @Author Kiran Pariyar <kiranpariyar@lftechnology.com>
 * Created on 1/25/16.
 */
public interface Validator<T> {

  Map<String, String> validate(T entity);
}
