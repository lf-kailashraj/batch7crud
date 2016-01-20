package com.lftechnology.batch7crud.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ReleaseResource {
    private static final Logger LOGGER = Logger.getLogger("ReleaseResourceLog");
    public void closeConnection(ResultSet rs, Connection conn, PreparedStatement ps)  {
        try {
            if (rs != null)
                rs.close();

            if (conn != null)
                conn.close();

            if (ps != null)
                ps.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

}
