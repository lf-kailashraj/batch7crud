package com.lftechnology.batch7crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lftechnology.batch7crud.constant.UserConstants;
import com.lftechnology.batch7crud.controller.UserController;
import com.lftechnology.batch7crud.db.DbConnector;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.User;
import com.mysql.jdbc.Statement;

public class UserDAOImpl implements UserDAO {

  private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());
  Connection connection = null;

  public UserDAOImpl() {
    try {
      this.connection = DbConnector.getMySqlConnection();
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }

  }

  @Override
  public void add(User user) throws DataException {
    String query = "insert into user (firstname,surname,username,password) values (?,?,?,?)";

    try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {

      preparedStatement.setString(1, user.getFirstName());
      preparedStatement.setString(2, user.getSurName());
      preparedStatement.setString(3, user.getUserName());
      preparedStatement.setString(4, user.getPassword());

      preparedStatement.executeUpdate();

      ResultSet rs = preparedStatement.getGeneratedKeys();
      if (rs.next()) {
        user.setId(rs.getInt(1));
      }
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException();
    }
  }

  @Override
  public void delete(int userID) throws DataException {
    String query = "delete from user where id= ?";

    try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
      preparedStatement.setLong(1, userID);
      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  @Override
  public List<User> fetch(int offset, int limit) throws DataException {
    String query = "select * from user order by id  limit ? offset ?";
    List<User> userList = new ArrayList<>();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setInt(1, limit);
      preparedStatement.setInt(2, offset);
      ResultSet results = preparedStatement.executeQuery();

      while (results.next()) {
        User user = new User();

        user.setId(results.getInt(UserConstants.UID));
        user.setFirstName(results.getString(UserConstants.FIRST_NAME));
        user.setSurName(results.getString(UserConstants.SUR_NAME));
        user.setUserName(results.getString(UserConstants.USERNAME));
        user.setPassword(results.getString(UserConstants.PASSWORD));

        userList.add(user);

      }

    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
    return userList;

  }

  @Override
  public User fetchByID(int userID) throws DataException {
    User user = null;
    String query = "select * from user where id=?";

    try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
      preparedStatement.setLong(1, userID);
      ResultSet results = preparedStatement.executeQuery();

      while (results.next()) {
        user = new User();

        user.setId(results.getInt(UserConstants.UID));
        user.setFirstName(results.getString(UserConstants.FIRST_NAME));
        user.setSurName(results.getString(UserConstants.SUR_NAME));
        user.setUserName(results.getString(UserConstants.USERNAME));
        user.setPassword(results.getString(UserConstants.PASSWORD));
        user.setAge(results.getInt(UserConstants.AGE));

      }

    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
    return user;

  }

  @Override
  public void update(User user) throws DataException {
    String query = " update user set firstname = ?, surname = ?, username=? where id = ?";

    try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {

      preparedStatement.setString(1, user.getFirstName());
      preparedStatement.setString(2, user.getSurName());
      preparedStatement.setString(3, user.getUserName());
      preparedStatement.setInt(4, user.getId());

      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  @Override
  public int totalUser() {
    String query = "select count(*) from user ";
    int totalNumber = 0;

    try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        totalNumber = resultSet.getInt(1);
      }
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
    return totalNumber;
  }

  @Override
  public User fetchUser(String username, String password) throws DataException {
    User user = null;

    String query = "select * from user where username = ? and password = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
      preparedStatement.setString(1, username);
      preparedStatement.setString(2, password);
      ResultSet results = preparedStatement.executeQuery();

      while (results.next()) {
        user = new User();

        user.setId(results.getInt(UserConstants.UID));
        user.setFirstName(results.getString(UserConstants.FIRST_NAME));
        user.setSurName(results.getString(UserConstants.SUR_NAME));
        user.setUserName(results.getString(UserConstants.USERNAME));
        user.setPassword(results.getString(UserConstants.PASSWORD));
      }

    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
    return user;
  }

}
