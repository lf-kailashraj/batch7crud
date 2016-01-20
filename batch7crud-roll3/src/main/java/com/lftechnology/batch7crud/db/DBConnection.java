package com.lftechnology.batch7crud.db;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.lftechnology.batch7crud.exception.DataException;
import java.sql.Connection;

public class DBConnection {
  private static final Logger LOGGER = Logger.getLogger("DBConnection");

  private DBConnection() {
  }

  public static Connection getConnection() throws DataException {
    try {
      Context initCtx = new InitialContext();

      Context envCtx = (Context) initCtx.lookup("java:comp/env");

      DataSource ds = (DataSource)

      envCtx.lookup("jdbc/students_info");

      return ds.getConnection();
    } catch (SQLException | NamingException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);

      throw new DataException(e.getMessage());
    }
  }
}
