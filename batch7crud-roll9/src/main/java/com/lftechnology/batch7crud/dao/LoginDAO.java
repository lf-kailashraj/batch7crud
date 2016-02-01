package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.entity.User;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by sanjay on 1/29/16.
 */
public class LoginDAO {
  private static final String USER_AUTHENTICATE = "SELECT COUNT(*) FROM user_login WHERE username=? AND password=?";

  private static final Logger LOGGER = Logger.getLogger(LoginDAO.class.getName());

  public boolean authenticate(User user) throws DataException{
    boolean state = false;
    try(Connection conn = DBConnection.getConnection(); PreparedStatement pstmt =  conn.prepareStatement(USER_AUTHENTICATE)) {
      pstmt.setString(1, user.getUsername());
      pstmt.setString(2, user.getPassword());
      ResultSet rs = pstmt.executeQuery();
      if (rs.next() && rs.getInt(1)>0){
          state = true;
      }
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException();
    }
    return state;
  }

}
