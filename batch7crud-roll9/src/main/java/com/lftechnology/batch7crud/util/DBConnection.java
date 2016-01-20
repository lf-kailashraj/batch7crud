package com.lftechnology.batch7crud.util;

import com.lftechnology.batch7crud.exception.DataException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by sanjay on 1/18/16.
 */
public class DBConnection {
    private static Connection conn = null;
    private static final Logger logger = Logger.getLogger("DBConnectionLog");
    private DBConnection(){}

    public static Connection getConnection() throws DataException {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/db_userinfo_test");
            conn = ds.getConnection();
            return conn;
        } catch (NamingException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw new DataException();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw new DataException();
        }

    }
}
