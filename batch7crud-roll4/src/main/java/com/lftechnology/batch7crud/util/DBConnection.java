package com.lftechnology.batch7crud.util;

import java.sql.*;

/**
 * Created by pratishshr on 1/14/16.
 */
public class DBConnection {
    private Connection connection;
    private PreparedStatement stmt;

    public DBConnection() throws ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
    }

    public void open() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost/ems","root","damcare56");
    }

    public PreparedStatement initStatement(String sql) throws SQLException {
        stmt = connection.prepareStatement(sql);
        return stmt;
    }

    public void executeUpdate(String sql) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(sql);
    }

    public void executeUpdate() throws SQLException {
        stmt.executeUpdate();
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(sql);
    }

    public void close() throws SQLException {
        if(!connection.isClosed()) {
            connection.close();
        }
    }

}
