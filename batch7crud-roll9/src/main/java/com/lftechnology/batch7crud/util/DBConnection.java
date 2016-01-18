package com.lftechnology.batch7crud.util;

import com.lftechnology.batch7crud.exception.DataException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by sanjay on 1/18/16.
 */
public class DBConnection {
    private static Connection conn = null;

    public static Connection getConnection() throws DataException {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/db_userinfo_test");
            conn = ds.getConnection();
            return conn;
        } catch (NamingException ex) {
            throw new DataException();
        } catch (SQLException ex) {
            throw new DataException();
        }

    }
}
