package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.db.DBConnection;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.User;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Grishma Shrestha grishmashrestha@lftechnology.com on 1/18/16.
 */

public class UserDao {
  private static final Logger LOGGER = Logger.getLogger(UserDao.class.getName());
  private static final String USER_INSERT = "insert into users (name, username, email, password) values (?,?,?,?)";
  private static final String USER_FIND_BY_USERNAME = "select * from users where username = ? and password = ?";

  public User create(User user) throws DataException {
    try (Connection conn = DBConnection.getConnection();
      PreparedStatement statement = conn.prepareStatement(USER_INSERT, Statement.RETURN_GENERATED_KEYS)
    ){
      statement.setString(1, user.getName());
      statement.setString(2, user.getUsername());
      statement.setString(3, user.getName());
      statement.setString(4, user.getPassword());
      statement.executeUpdate();
      ResultSet resultSet = statement.getGeneratedKeys();
      if (resultSet.next()) {
        user.setId(resultSet.getInt(1));
      }
      return user;
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  public User fetchUserByUsernameAndPassword (User user) throws DataException {
    try (Connection conn = DBConnection.getConnection();
      PreparedStatement statement = conn.prepareStatement(USER_FIND_BY_USERNAME)
    ){
      statement.setString(1, user.getUsername());
      statement.setString(2, user.getPassword());
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        user.setId(resultSet.getInt(1));
        user.setName(resultSet.getString(2));
        user.setEmail(resultSet.getString(3));
        return user;
      }
      else {
        return null;
      }
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

}
