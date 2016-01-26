package com.lftechnology.batch7crud.util;

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
 * Created by Pratish Shrestha <pratishshrestha@lftechnology.com> on 1/14/16.
 */
public class DbConnection {
  private static final Logger LOGGER = Logger.getLogger(DbConnection.class.getName());

  private DbConnection() {

  }

  public static Connection getConnection() throws DataException {
    try {
      Context initCtx = new InitialContext();
      Context envCtx = (Context) initCtx.lookup("java:comp/env");
      DataSource ds = (DataSource) envCtx.lookup("jdbc/ems");

      return ds.getConnection();

    } catch (SQLException | NamingException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }

  }

}
