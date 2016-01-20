package com.lftechnology.batch7crud.utils;

import com.lftechnology.batch7crud.exception.DataException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
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
    } catch (NamingException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException();
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException();
    }
  }

  public static void closePreparedStatement(PreparedStatement... ps) {
    for (PreparedStatement p : ps) {
      if (p != null) {
        try {
          p.close();
        } catch (SQLException e) { // NOSONAR
        }
      }
    }
  }

  public static void closeResultSet(ResultSet... rs) {
    for (ResultSet r : rs) {
      if (r != null) {
        try {
          r.close();
        } catch (SQLException e) { // NOSONAR
        }
      }

    }
  }

  public static void closeConnection(Connection conn) {
    if (conn != null) {
      try {
        conn.close();
      } catch (SQLException e) { // NOSONAR
      }
    }
  }
}
