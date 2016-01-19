package com.lftechnology.batch7crud.util;

import com.lftechnology.batch7crud.exception.DataException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by pratishshr on 1/14/16.
 */
public class DbConnection {
    private static Logger logger = Logger.getLogger("DbConnectionLog");

    private DbConnection() {

    }

    public static Connection getConnection() throws DataException {

        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ems");

            return ds.getConnection();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
            throw new DataException(e.getMessage());
        } catch (NamingException e) {
            logger.log(Level.SEVERE, e.getMessage());
            throw new DataException(e.getMessage());
        }
    }
}
