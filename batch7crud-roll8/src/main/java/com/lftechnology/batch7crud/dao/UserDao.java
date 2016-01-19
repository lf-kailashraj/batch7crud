package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.db.DBConnection;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.User;

import javax.xml.crypto.Data;
import java.beans.Statement;
import java.sql.*;

/**
 * Created by grishma on 1/18/16.
 */
public class UserDao {
    public void create(User user) throws DataException {
        Statement stmt = null;

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
            throw new DataException();
        }
    }

}
