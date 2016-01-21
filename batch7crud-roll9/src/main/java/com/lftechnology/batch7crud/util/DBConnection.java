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
 * Created by sanjay on 1/18/16.
 */
public class DBConnection {
  private static Connection conn = null;
  private static final Logger LOGGER = Logger.getLogger(DBConnection.class.getName());

  private static final String DB_NAME = "jdbc/db_userinfo_test";
  private static final String DB_CONTEXT = "java:/comp/env";


  private DBConnection() {
  }

  public static Connection getConnection() throws DataException {
    try {
      Context initContext = new InitialContext();
      Context envContext = (Context) initContext.lookup(DB_CONTEXT);
      DataSource ds = (DataSource) envContext.lookup(DB_NAME);
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

}
