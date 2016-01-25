package com.lftechnology.batch7crud.validator;

import com.lftechnology.batch7crud.entity.Student;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Binod Shrestha <binodshrestha@lftechnology.com>
 * Created on 1/25/16
 */
public class StudentValidator {
  private static final String NAME_ERROR = "nameError";
  private static final String ADDRESS_ERROR = "addressError";
  private static final String DEPARTMENT_ERROR = "departmentError";
  private static final String BATCH_ERROR = "batchError";
  private static final String ROLL_ERROR = "ROLLError";

  public List<Pair> validate(Student student){
    List<Pair> errors = new ArrayList<>();

    if(student.getName() == null || student.getName().isEmpty()){
      errors.add(new Pair<>(NAME_ERROR, "invalid name"));
    }

    if(student.getAddress() == null || student.getAddress().isEmpty()){
      errors.add(new Pair<>(ADDRESS_ERROR, "invalid address"));

    }

    if(student.getDepartment() == null || student.getDepartment().isEmpty()){
      errors.add(new Pair<>(DEPARTMENT_ERROR, "invalid department"));

    }

    if(student.getBatch() == null || student.getBatch().isEmpty()){
      errors.add(new Pair<>(BATCH_ERROR, "invalid batch"));

    }

    if(student.getRoll() < 1){
      errors.add(new Pair<>(ROLL_ERROR, "invalid roll number"));

    }

    return errors;
  }
}
