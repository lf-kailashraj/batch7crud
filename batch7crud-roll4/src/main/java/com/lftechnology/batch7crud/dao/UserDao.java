package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.util.DbConnection;
import com.lftechnology.batch7crud.util.Md5;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by pratishshr on 1/27/16.
 */
public class UserDao {
  private static final Logger LOGGER = Logger.getLogger(EmployeeDao.class.getName());

  public boolean isAvailable(String username, String password) throws DataException {

    try (Connection connection = DbConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT count(*) FROM user WHERE username = ? && password = ?")) {
      int noOfRecords = 0;
      stmt.setString(1, username);
      stmt.setString(2, Md5.getMD5(password));

      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        noOfRecords = rs.getInt(1);
      }
      return noOfRecords == 1; //returns true if equals 1

    } catch (SQLException | NoSuchAlgorithmException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

}
