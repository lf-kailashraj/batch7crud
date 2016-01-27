package com.lftechnology.batch7crud.factory;

import java.util.Map;

/**
 * Created by Grishma Shrestha grishmashrestha@lftechnology.com on 1/26/16.
 */
public interface Factory <T> {
  public T createObject(Map<String, String> input);
}
