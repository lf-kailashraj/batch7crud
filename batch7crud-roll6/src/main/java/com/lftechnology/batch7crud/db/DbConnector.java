package com.lftechnology.batch7crud.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DbConnector {

    public static Connection getMySqlConnection() throws SQLException {
        Connection connection = null;
        try {
            Context initCtx = new InitialContext();

            Context envCtx = (Context) initCtx.lookup("java:comp/env");

            DataSource ds = (DataSource) envCtx.lookup("jdbc/dbInitial");
            connection = ds.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
