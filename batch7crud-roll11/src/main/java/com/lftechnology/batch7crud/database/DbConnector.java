package com.lftechnology.batch7crud.database;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by achyut on 1/26/16.
 */
public class DbConnector {
  private static final String DB_NAME = "jdbc/employee";
  private static final String DB_CONTEXT = "java:/comp/env";

  private DbConnector() {

  }

  public static Connection getConnection() throws SQLException, NamingException {
    Connection connection = null;
    try {
      Context initContext = new InitialContext();
      Context envContext = (Context) initContext.lookup(DB_CONTEXT);
      DataSource ds = (DataSource) envContext.lookup(DB_NAME);
      connection = ds.getConnection();
    } catch (NamingException e) {
      throw e;
    }catch (SQLException e){
      throw e;
    }
    return connection;
  }
}