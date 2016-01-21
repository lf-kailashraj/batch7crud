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
 * This class is used to provide the Connection object.
 *
 * @Author Binod Shrestha <binodshrestha@lftechnology.com>
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
        dataSource = (DataSource) envContext.lookup("jdbc/library");
      }
      return dataSource.getConnection();
    } catch (NamingException | SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException();
    }
  }
}
