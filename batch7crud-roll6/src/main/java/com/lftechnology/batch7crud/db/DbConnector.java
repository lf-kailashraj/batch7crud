package com.lftechnology.batch7crud.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DbConnector {

     private static String USERNAME = "root";
     private static String PASSWORD = "root";
     private static String MYSQL_DATABASE="jdbc:mysql://localhost/dbInitial";
    //
    public static Connection getMySqlConnection() throws SQLException {
        Connection connection = null;
        try {
             DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
             connection = DriverManager.getConnection(MYSQL_DATABASE,USERNAME,PASSWORD);
//            Context initCtx = new InitialContext();
//
//            Context envCtx = (Context) initCtx.lookup("java:comp/env");
//
//            DataSource ds = (DataSource) envCtx.lookup("jdbc/dbInitial");
//            connection = ds.getConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return connection;
    }
}
