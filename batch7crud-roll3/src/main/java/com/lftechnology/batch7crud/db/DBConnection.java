package com.lftechnology.batch7crud.db;

import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.lftechnology.batch7crud.exception.DataException;
import java.sql.Connection;

public class DBConnection {
	public static Connection getConnection() throws DataException {
		try {
			Context initCtx = new InitialContext();

			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			DataSource ds = (DataSource)

			envCtx.lookup("jdbc/students_info");

			Connection conn = ds.getConnection();
			return conn;
		} catch (SQLException e) {
			throw new DataException(e.getMessage());
		} catch (NamingException e) {
			throw new DataException(e.getMessage());
		}
	}
}
