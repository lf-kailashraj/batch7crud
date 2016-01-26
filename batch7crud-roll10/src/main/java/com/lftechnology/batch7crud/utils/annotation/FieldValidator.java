package com.lftechnology.batch7crud.utils.annotation;

import com.lftechnology.batch7crud.exception.ValidationException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author binodnme
 * Created on 1/26/16
 */
public class FieldValidator {
  private static final Logger LOGGER = Logger.getLogger(FieldValidator.class.getName());

  private FieldValidator(){
  }

  public static void validate(Object obj) throws ValidationException {
    Class aClass = obj.getClass();

    List<Field> fields = new ArrayList<>();
    fields = getAllFields(fields, aClass);

    Map<String, String> errors = new HashMap<>();
    for (Field field : fields) {
      //this makes the private field accessible
      field.setAccessible(true);

      Annotation[] annotations = field.getDeclaredAnnotations();

      for (Annotation a : annotations) {
        if (a instanceof Required) {
          try {
            if (field.get(obj) == null || field.get(obj).toString().isEmpty()) {
              errors.put(field.getName(), " cannot be null or empty");
            }
          } catch (IllegalAccessException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
          }
        }
      }
    }

    if (!errors.isEmpty()) {
      throw new ValidationException("validation failed", errors);
    }

  }


  public static List<Field> getAllFields(List<Field> fields, Class<?> type) {
    fields.addAll(Arrays.asList(type.getDeclaredFields()));

    if (type.getSuperclass() != null) {
      fields = getAllFields(fields, type.getSuperclass());
    }

    return fields;
  }
}
