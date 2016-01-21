package com.lftechnology.batch7crud.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Author Binod Shrestha <binodshrestha@lftechnology.com>
 * Created on 1/20/16
 */
public class DateUtils {

  private DateUtils() {
  }

  public static Date parse(String date) throws ParseException {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
    return dateFormat.parse(date);
  }
}
