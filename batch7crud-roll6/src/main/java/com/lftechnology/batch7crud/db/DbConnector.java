package com.lftechnology.batch7crud.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.lftechnology.batch7crud.controller.UserController;

public class DbConnector {

  private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

  private DbConnector() {

  }

  public static Connection getMySqlConnection() throws SQLException {
    Connection connection = null;
    try {
      Context initCtx = new InitialContext();

      Context envCtx = (Context) initCtx.lookup("java:comp/env");

      DataSource ds = (DataSource) envCtx.lookup("jdbc/dbInitial");
      connection = ds.getConnection();
    } catch (NamingException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }

    return connection;
  }
}
