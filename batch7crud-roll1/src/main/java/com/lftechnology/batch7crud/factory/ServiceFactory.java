package com.lftechnology.batch7crud.factory;

import com.lftechnology.batch7crud.service.EmployeeService;
import com.lftechnology.batch7crud.service.EmployeeServiceImpl;

/**
 * ServiceFactory creates and returns Service Object
 *
 * @Author Kiran Pariyar <kiranpariyar@lftechnology.com>
 * Created on 1/26/16.
 */

public class ServiceFactory {

  private ServiceFactory() {

  }

  public static EmployeeService createEmployeeService() {
    return new EmployeeServiceImpl();
  }
}
