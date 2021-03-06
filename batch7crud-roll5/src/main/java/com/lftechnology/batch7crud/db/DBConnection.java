package com.lftechnology.batch7crud.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.lftechnology.batch7crud.constants.NormalConstants;
import com.lftechnology.batch7crud.exception.DataException;

public class DBConnection {
    private static final Logger LOGGER = Logger.getLogger(DBConnection.class.getName());

    private DBConnection() {

    }

    public static Connection getConnection() throws DataException {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup(NormalConstants.DATABASE);
            return ds.getConnection();

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DataException(e.getMessage());

        } catch (NamingException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DataException(e.getMessage());

        }

    }
}
