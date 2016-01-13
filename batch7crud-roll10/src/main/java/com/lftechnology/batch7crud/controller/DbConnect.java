package com.lftechnology.batch7crud.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author binodnme
 * Created on 1/13/16
 */
public class DbConnect {
//    private static String dbUrl = "jdbc:postgresql://localhost/lftest";
    private static String dbUrl = "jdbc:mysql://localhost/lftest";
    private static String username = "root";
//    private static String username = "postgres";
    private static String password = "password";
    private static Connection conn = null;
    private static Statement stmt = null;


    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
//        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection(dbUrl, username, password);
        return conn;

    }

}
