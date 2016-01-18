package com.lftechnology.batch7crud.util;


import com.lftechnology.batch7crud.exception.DataException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class DBConnection {
    public static Connection getSqlConnection() throws DataException {
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/formdb");
            Connection con = ds.getConnection();
            return con;
        } catch (NamingException e) {
            throw new DataException(e.getMessage());
        } catch (SQLException e) {
            throw new DataException(e.getMessage());
        }
    }
}
