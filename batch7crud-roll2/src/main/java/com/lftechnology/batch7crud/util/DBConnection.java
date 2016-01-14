package com.lftechnology.batch7crud.util;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;

public class DBConnection{
    public static Connection getPostgreSqlConnection() {
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource)
                    envCtx.lookup("jdbc/formdb");
            Connection con = ds.getConnection();
            return con;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
