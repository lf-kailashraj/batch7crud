package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.db.DBConnection;
import com.lftechnology.batch7crud.exception.DataException;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by grishma on 1/18/16.
 */
public class UserDao {
    public void create(String name, String username, String email, String password) throws DataException {
        try {
            String sql = "INSERT INTO USERS (name, username, email, password) VALUES (name, username, email, password)";
            Connection conn = DBConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
        }
        catch (SQLException e) {
            throw new DataException();
        }

    }
}
