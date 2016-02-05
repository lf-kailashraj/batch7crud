package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * UserDAO performs database operation in user table
 * <p>
 * Created by kiran on 1/29/16.
 */
public class UserDAO {

  private static final Logger LOGGER = Logger.getLogger(EmployeeDAO.class.getName());

  public boolean isValidUser(String userName, String password) throws DataException {
    String sqlQuery = "SELECT * FROM users WHERE userName=? AND password=?";   //NOSONAR
    try (Connection connection = DbUtils.getConnection();
            PreparedStatement ps = connection.prepareStatement(sqlQuery)
    ) {

      boolean isUserExist = false;
      ps.setString(1, userName);
      ps.setString(2, password);
      ResultSet resultSet = ps.executeQuery();
      if (resultSet.next()) {
        isUserExist = true;
      }
      return isUserExist;
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }
}
