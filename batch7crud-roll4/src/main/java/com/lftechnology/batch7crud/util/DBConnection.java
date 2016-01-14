package com.lftechnology.batch7crud.util;

import java.sql.*;

/**
 * Created by pratishshr on 1/14/16.
 */
public class DBConnection {
    private Connection connection;

    public DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost/ems","root","damcare56");

    }

    public int executeUpdate(String sql) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.executeUpdate(sql);
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(sql);
    }


}
