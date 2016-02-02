package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.database.DbConnector;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.User;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by achyut on 2/2/16.
 */
public class UserDaoImpl implements UserDao{
  private static final String ADD_USER = "INSERT INTO users(username, password) VALUES (?, ?)";
  private static final String CHECK_USER = "SELECT count(uid) AS totalUser FROM users WHERE username = ? AND password = ?";

  private static final Logger LOGGER = Logger.getLogger(UserDao.class.getName());

  @Override
  public void create(User user) throws DataException{
    try(Connection conn = DbConnector.getConnection(); PreparedStatement ps = conn.prepareStatement(ADD_USER);){
      ps.setString(1, user.getUserName());
      ps.setString(2, user.getPassword());
      ps.execute();
    }catch(SQLException e){
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  @Override
  public boolean checkUser(User user) throws DataException{
    try(Connection conn = DbConnector.getConnection(); PreparedStatement ps = conn.prepareStatement(CHECK_USER)){
      ps.setString(1, user.getUserName());
      ps.setString(2, user.getPassword());
      ResultSet rs = ps.executeQuery();
      while(rs.next()){
         return rs.getInt("totalUser") == 1 ? true : false;
      }
      return false;
    }catch (SQLException e)
    {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }
}
