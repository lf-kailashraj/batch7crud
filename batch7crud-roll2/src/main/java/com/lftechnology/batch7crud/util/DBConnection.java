package com.lftechnology.batch7crud.util;

import com.lftechnology.batch7crud.exception.DataException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Romit Amgai <romitamgai@lftechnology.com> on 1/19/16.
 */

public class DBConnection {
  private static final Logger LOGGER = Logger.getLogger("dbConnectionLogger");

  private DBConnection() {

  }

  public static Connection getSqlConnection() throws DataException {
    try {
      Context initCtx = new InitialContext();
      Context envCtx = (Context) initCtx.lookup("java:comp/env");
      DataSource ds = (DataSource) envCtx.lookup("jdbc/formdb");
      return ds.getConnection();
    } catch (NamingException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  public static void closeResultSets(ResultSet... rs) {
    for (ResultSet r : rs) {
      if (r != null) {
        try {
          r.close();
        } catch (SQLException e) { //NOSONAR
        }
      }
    }
  }

  public static void closePreparedStatements(PreparedStatement... ps) {
    for (PreparedStatement p : ps) {
      if (p != null) {
        try {
          p.close();
        } catch (SQLException e) { //NOSONAR

        }
      }
    }
  }

  public static void closeConnection(Connection con) {
    if (con != null) {
      try {
        con.close();
      } catch (SQLException e) { //NOSONAR

      }
    }
  }
}
