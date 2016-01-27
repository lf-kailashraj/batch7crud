package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.db.DbUtilities;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by leapfrog on 1/27/16.
 */
public class UserDao {
  private static final Logger LOGGER = Logger.getLogger(UserDao.class.getName());
  private static final String FETCH_USER = "select * from users where username = ? and password = ?";

  public User fetch(String name,String password) throws DataException{
    try(Connection conn = DbUtilities.getConnection(); PreparedStatement ps = conn.prepareStatement(FETCH_USER)) {
      ps.setString(1,name);
      ps.setString(2,password);
      ResultSet rs = ps.executeQuery();

      User user = null;

      if(rs.next()){
        user = new User(rs.getString("username"),rs.getString("password"),rs.getInt("id"));
      }
      return user;

    } catch (SQLException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      throw new DataException();
    }
  }
}
