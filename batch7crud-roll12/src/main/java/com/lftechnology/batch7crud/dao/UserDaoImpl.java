package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.model.User;
import com.lftechnology.batch7crud.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by sagarmatha on 2/2/16.
 */
public class UserDaoImpl implements UserDao {
  private static final Logger logger = Logger.getLogger(UserDaoImpl.class.getName());
  private static final String VIEW = "SELECT * FROM admin";

  Connection connection;
  PreparedStatement preparedStatement;

  public User getUser() {
    User user = null;
    try {
      connection = DBConnection.getDBConnection();
      preparedStatement = connection.prepareStatement(VIEW);
      ResultSet rs = preparedStatement.executeQuery();
      if (rs.next()) {
        user = new User();
        user.setUserID(rs.getInt(1));
        user.setUserName(rs.getString(2));
        user.setPassWord(rs.getString(3));
      }

    } catch (SQLException e) {
      logger.log(Level.SEVERE, e.getMessage(), e);
    } finally {
      try {
        preparedStatement.close();
        connection.close();
      } catch (SQLException e) {
        logger.log(Level.SEVERE, e.getMessage(), e);
      }
    }
    return user;
  }
}
