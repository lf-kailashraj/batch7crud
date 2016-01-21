package com.lftechnology.batch7crud.db;

import com.lftechnology.batch7crud.exception.DataException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Grishma Shrestha grishmashrestha@lftechnology.com on 1/18/16.
 */

public class DBConnection {
  public static Connection getConnection() throws DataException {
    try {
      Context initContext = new InitialContext();
      Context envContext = (Context) initContext.lookup("java:/comp/env");
      DataSource dataSource = (DataSource) envContext.lookup("jdbc/employeemgt");
      Connection conn = dataSource.getConnection();
      return conn;
    } catch (SQLException e) {
      throw new DataException();
    } catch (NamingException e) {
      throw new DataException();
    }
  }
}
