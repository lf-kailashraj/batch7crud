package com.lftechnology.batch7crud.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lftechnology.batch7crud.db.DBConnection;
import com.lftechnology.batch7crud.entity.User;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.utils.Md5Converter;

public class AuthenticationDao {
  private static final Logger LOGGER = Logger.getLogger(AuthenticationDao.class.getName());

  public boolean isValid(User user) throws DataException {
    try (Connection conn = DBConnection.getConnection();
        PreparedStatement stmnt = conn
            .prepareStatement("Select count(*) from Users where username=? and  password=?")) {
      Md5Converter converter = new Md5Converter();

      user.setPassword(converter.convert(user.getPassword()));
      stmnt.setString(1, user.getName());
      stmnt.setString(2, user.getPassword());
      int number = 0;
      try (ResultSet result = stmnt.executeQuery();) {

        if (result.next()) {
          number = result.getInt(1);
        }
        return number == 1;
      }
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);

      throw new DataException(e.getMessage());
    } catch (NoSuchAlgorithmException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
    return false;
  }
}