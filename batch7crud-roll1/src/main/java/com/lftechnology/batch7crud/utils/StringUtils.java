package com.lftechnology.batch7crud.utils;

/**
 * StringUtils provide access to string related functions
 *
 * @Author Kiran Pariyar <kiranpariyar@lftechnology.com>
 * Created on 1/27/16.
 */
public class StringUtils {

  private StringUtils() {

  }

  public static boolean isNullOrEmpty(String value) {

    return value == null || value.trim().isEmpty();
  }
}
