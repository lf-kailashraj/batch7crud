package com.lftechnology.batch7crud.utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author binodnme
 * Created on 1/18/16
 */
public class DbConnection {
    private DbConnection(){}

    public static Connection getConnection() throws NamingException, SQLException {
        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:/comp/env");
        DataSource ds = (DataSource) envContext.lookup("jdbc/library");
        return ds.getConnection();
    }
}
