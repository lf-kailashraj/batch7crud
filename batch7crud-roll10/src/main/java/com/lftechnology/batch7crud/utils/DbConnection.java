package com.lftechnology.batch7crud.utils;

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
 * @Author binodnme
 * Created on 1/18/16
 */
public class DbConnection {

    private static Logger logger = Logger.getLogger(DbConnection.class.getName());

    private DbConnection() {
    }

    public static Connection getConnection() throws DataException{

        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/library");
            return ds.getConnection();
        } catch (NamingException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new DataException();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new DataException();
        }
    }
}
