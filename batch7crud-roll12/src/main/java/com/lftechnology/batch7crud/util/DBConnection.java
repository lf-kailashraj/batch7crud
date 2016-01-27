package com.lftechnology.batch7crud.util;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by sagarmatha on 1/26/16.
 */
public class DBConnection {
    private static DataSource ds;
    public static Connection getDBConnection() {
        Connection conn = null;
        try {
            InitialContext context = new InitialContext();
            ds = (DataSource) context.lookup("java:comp/env/jdbc/student");
            conn = ds.getConnection();
        } catch (NamingException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
}
