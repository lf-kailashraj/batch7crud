package com.lftechnology.batch7crud.db;

import com.lftechnology.batch7crud.exception.DataException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by leapfrog on 1/18/16.
 */
public class DbUtilities {
    public static PreparedStatement getPreparedStatement(String sql) throws DataException {
        try {
            PreparedStatement ps = null;
            Connection conn = null;
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/lfform");
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            return ps;
        } catch (SQLException ex) {
            throw new DataException();
        } catch (NamingException ex) {
            throw new DataException();
        }

    }
}