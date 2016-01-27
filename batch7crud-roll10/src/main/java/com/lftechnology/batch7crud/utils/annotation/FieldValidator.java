package com.lftechnology.batch7crud.utils.annotation;

import com.lftechnology.batch7crud.exception.ValidationException;

import java.lang.reflect.Field;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class validates the field w.r.t the annotation used in that field
 *
 * @Author binodnme
 * Created on 1/26/16
 */
public class FieldValidator {
  private static final Logger LOGGER = Logger.getLogger(FieldValidator.class.getName());

  private FieldValidator() {
  }

  public static void validate(Object obj) throws ValidationException {
    Class aClass = obj.getClass();

    List<Field> fields = new ArrayList<>();
    fields = getAllFields(fields, aClass);

    Map<String, String> errors = new HashMap<>();
    for (Field field : fields) {
      //this makes the private field accessible
      field.setAccessible(true);

      if (field.isAnnotationPresent(Required.class)) {
        inspect(obj, errors, field);
      }
    }
    if (!errors.isEmpty()) {
      throw new ValidationException("validation failed", errors);
    }

  }

  private static void inspect(Object obj, Map<String, String> errors, Field field) {
    try {
      if (!isValid(obj, field)) {
        errors.put(field.getName(), " cannot be null or empty");
      }
    } catch (IllegalAccessException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  private static boolean isValid(Object obj, Field field) throws IllegalAccessException {
    return field.get(obj) != null && !field.get(obj).toString().isEmpty();
  }

  public static List<Field> getAllFields(List<Field> fields, Class<?> type) {
    fields.addAll(Arrays.asList(type.getDeclaredFields()));

    if (type.getSuperclass() != null) {
      fields = getAllFields(fields, type.getSuperclass());
    }

    return fields;
  }
}
