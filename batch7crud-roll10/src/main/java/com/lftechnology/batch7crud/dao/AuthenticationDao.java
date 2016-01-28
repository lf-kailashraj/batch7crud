package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.entity.User;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.utils.DbUtils;
import com.lftechnology.batch7crud.utils.Md5Converter;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author binodnme
 * Created on 1/27/16
 */
public class AuthenticationDao {
  private static final Logger LOGGER = Logger.getLogger(AuthenticationDao.class.getName());
  private static final String USERNAME = "username";
  private static final String ROLE = "role";
  private static final String GET_USER = "select * from user_info where username=? and  password=? "; // NOSONAR

  public User isValid(String username, String password) throws DataException {

    String passwd;
    try {
      passwd = Md5Converter.getHashedText(password);

    } catch (NoSuchAlgorithmException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }

    User user = null;
    ResultSet rs;
    try (Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(GET_USER)
    ) {
      ps.setString(1, username);
      ps.setString(2, passwd);

      rs = ps.executeQuery();

      if (rs.next()) {
        user = new User();
        user.setUsername(rs.getString(USERNAME));
        user.setRole(rs.getString(ROLE));
      }
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException();
    }

    return user;
  }
}
