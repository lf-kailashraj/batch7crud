package com.lftechnology.batch7crud.util;

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
 * Created by sanjay on 1/18/16.
 */
public class DBConnection {
  private static Connection conn = null;
  private static final Logger LOGGER = Logger.getLogger("DBConnectionLog");

  private DBConnection() {
  }

  public static Connection getConnection() throws DataException {
    try {
      Context initContext = new InitialContext();
      Context envContext = (Context) initContext.lookup("java:/comp/env");
      DataSource ds = (DataSource) envContext.lookup("jdbc/db_userinfo_test");
      conn = ds.getConnection();
      return conn;
    } catch (NamingException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      throw new DataException();
    } catch (SQLException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      throw new DataException();
    }

  }
  public static void closePreparedStatement(PreparedStatement... ps) {
    for (PreparedStatement p : ps) {
      try {
        if(p != null)
          p.close();
      } catch (SQLException e) { // NOSONAR
      }
    }
  }

  public static void closeResultSet(ResultSet... rs) {
    for (ResultSet r : rs) {
      try {
        if(r != null)
        r.close();
      } catch (SQLException e) { // NOSONAR
      }
    }
  }

  public static void closeConnection(Connection conn) {
    if (conn != null) {
      try {
        if(conn != null )
        conn.close();
      } catch (SQLException e) { // NOSONAR
      }
    }
  }
}
