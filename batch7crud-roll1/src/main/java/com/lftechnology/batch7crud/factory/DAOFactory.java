package com.lftechnology.batch7crud.factory;

import com.lftechnology.batch7crud.dao.EmployeeDAO;
import com.lftechnology.batch7crud.dao.EmployeeDAOImpl;

/**
 * This Class creates and returns DAO object
 *
 * @Author Kiran Pariyar <kiranpariyar@lftechnology.com>
 * Created on 2/1/16.
 */

public class DAOFactory {

  private DAOFactory() {

  }

  public static EmployeeDAO createEmployeeDAO() {
    return new EmployeeDAOImpl();
  }
}
