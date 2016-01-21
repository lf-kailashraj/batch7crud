package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.db.DBConnection;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Grishma Shrestha grishmashrestha@lftechnology.com on 1/18/16.
 */

public class UserDao {
  private static final Logger LOGGER = Logger.getLogger(User.class.getName());

  public void create(User user) throws DataException {
    try {
      String sql = "INSERT INTO USERS (name, username, email, password) VALUES (?,?,?,?)";
      Connection conn = DBConnection.getConnection();
      PreparedStatement statement = conn.prepareStatement(sql);
      statement.setString(1, user.getName());
      statement.setString(2, user.getUsername());
      statement.setString(3, user.getName());
      statement.setString(4, user.getPassword());
      statement.executeUpdate();
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

}
