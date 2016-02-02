package com.lftechnology.batch7crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lftechnology.batch7crud.db.DBConnection;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Admin;

public class AdminDAOImpl {
    private static final String AUTHENTICATE_USER = "SELECT * FROM admin WHERE user_name = ? and password = ?";
    private static final Logger LOGGER = Logger.getLogger(EmployeeDAOImpl.class.getName());

    public Admin login(String name, String password) throws DataException {
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(AUTHENTICATE_USER)) {
            Admin admin = null;
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                admin = new Admin();
                admin = setObjectAttribute(rs, admin);
            }
            
            return admin;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DataException(e.getMessage());
        }
    }

    private Admin setObjectAttribute(ResultSet rs, Admin admin) throws SQLException {
        admin.setUserName(rs.getString("user_name"));
        admin.setPassword(rs.getString("password"));
        return admin;
    }
}
