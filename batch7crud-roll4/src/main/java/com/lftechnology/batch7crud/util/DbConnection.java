package com.lftechnology.batch7crud.util;

import com.lftechnology.batch7crud.exception.DataException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by pratishshr on 1/14/16.
 */
public class DbConnection {

    private DbConnection() {

    }

    public static Connection getConnection() throws DataException {
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ems");

            return ds.getConnection();

        } catch (SQLException e) {
            throw new DataException(e.getMessage());
        } catch (NamingException e) {
            throw new DataException(e.getMessage());
        }
    }
}
