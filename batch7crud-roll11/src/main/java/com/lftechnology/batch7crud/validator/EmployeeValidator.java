package com.lftechnology.batch7crud.validator;

import com.lftechnology.batch7crud.constant.EmployeeConstants;
import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.util.NumberUtil;
import com.lftechnology.batch7crud.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by achyut on 1/29/16.
 */
public class EmployeeValidator implements Validator<Employee> {
  Map<String, String> errors = null;
  @Override
  public void validateEmpty(Map<String, String> inputs) throws ValidationException{
    errors = new HashMap<>();
    if(StringUtil.isEmpty(inputs.get(EmployeeConstants.NAME)))
    {
      errors.put(EmployeeConstants.NAME,"Name field can't be empty");
    }
    if(StringUtil.isEmpty(inputs.get(EmployeeConstants.ADDRESS)))
    {
      errors.put(EmployeeConstants.ADDRESS,"Address field can't be empty");
    }
    if(StringUtil.isEmpty(inputs.get(EmployeeConstants.DEPARTMENT)))
    {
      errors.put(EmployeeConstants.DEPARTMENT,"Department field can't be empty");
    }
    if(StringUtil.isEmpty(inputs.get(EmployeeConstants.POSITION)))
    {
      errors.put(EmployeeConstants.POSITION,"Position field can't be empty");
    }
    if(StringUtil.isEmpty(inputs.get(EmployeeConstants.SALARY)))
    {
      errors.put(EmployeeConstants.SALARY,"Salary field can't be empty");
    }else if(!NumberUtil.isNumeric(inputs.get(EmployeeConstants.SALARY))){
      errors.put(EmployeeConstants.SALARY,"Salary must be in number");
    }

    if(!errors.isEmpty()){
      throw new ValidationException(errors);
    }
  }

  @Override
  public void validateBusinessLogic(Employee employee) throws ValidationException{
    errors = new HashMap<>();
    if(employee.getSalary()<=1000){
      errors.put(EmployeeConstants.SALARY, "Salary should be greater than 1000");
    }

    if(!errors.isEmpty()){
      throw new ValidationException(errors);
    }
  }
}