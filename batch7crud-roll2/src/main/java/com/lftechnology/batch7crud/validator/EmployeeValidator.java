package com.lftechnology.batch7crud.validator;

import com.lftechnology.batch7crud.constants.AppConstants;
import com.lftechnology.batch7crud.constants.AttributeConstants;
import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.model.Employee;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Romit Amgai <romitamgai@lftechnology.com> on 1/25/16.
 */
public class EmployeeValidator implements GenericValidator<Employee> {

  @Override
  public void validate(Employee entity) throws ValidationException {
    Map<String, String> errors = new HashMap<>();
    String email = entity.getEmail();

    if (isNullOrEmpty(entity.getName())) {
      errors.put(AttributeConstants.NAME, "Set Name");
    }
    if (isNullOrEmpty(entity.getAddress())) {
      errors.put(AttributeConstants.ADDRESS, "Set Address");
    }
    if (isNullOrEmpty(email) || !isValidEmail(email)) {
      errors.put(AttributeConstants.EMAIL, "Email not correct");
    }
    if (isNullOrEmpty(entity.getContact())) {
      errors.put(AttributeConstants.CONTACT, "Set Contact");
    }
    if (!errors.isEmpty()) {
      throw new ValidationException(errors);
    }
  }

  private boolean isNullOrEmpty(String value) {
    return value == null || value.trim().isEmpty();
  }

  private boolean isValidEmail(String email) {
    return email.matches(AppConstants.EMAIL_PATTERN);
  }
}
