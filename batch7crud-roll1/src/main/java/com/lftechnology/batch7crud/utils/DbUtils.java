package com.lftechnology.batch7crud.utils;

import com.lftechnology.batch7crud.exception.DataException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author Kiran Pariyar <kiranpariyar@lftechnology.com>
 * Created on 1/18/16
 */

public class DbUtils {
  private static DataSource dataSource;
  private static final Logger LOGGER = Logger.getLogger(DbUtils.class.getName());

  private DbUtils() {
  }

  public static Connection getConnection() throws DataException {

    try {
      if (dataSource == null) {
        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:/comp/env");
        dataSource = (DataSource) envContext.lookup("jdbc/lf_testdatabase");
      }
      return dataSource.getConnection();
    } catch (NamingException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException();
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }
}
