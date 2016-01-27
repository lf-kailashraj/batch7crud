package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Romit Amgai <romitamgai@lftechnology.com> on 1/27/16.
 */
public class AdminDao {
  private static final Logger LOGGER = Logger.getLogger(AdminDao.class.getName());
  private static final String ADMIN_SELECT = "SELECT id FROM admin WHERE username=? AND password=?";

  public boolean isValid(String userName, String password) throws DataException {
    try (Connection con = DBConnection.getSqlConnection();
            PreparedStatement preStmt = con.prepareStatement(ADMIN_SELECT)) {
      int id = 0;
      preStmt.setString(1, userName);
      preStmt.setString(2, password);
      ResultSet resultSet = preStmt.executeQuery();
      while (resultSet.next()) {
        id = resultSet.getInt("id");
      }
      if (id != 0) {
        return true;
      }
      return false;
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }
}
