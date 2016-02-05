package com.lftechnology.batch7crud.validator;

import java.util.Map;

/**
 * Created by sagarmatha on 2/4/16.
 */
public interface FormValidator {
  boolean validateInput(Map<String, String> data);

  boolean fnameCheck(String firstName);

  boolean lnameCheck(String lastName);

  boolean ageCheck(String age);

  boolean addressCheck(String address);
}
