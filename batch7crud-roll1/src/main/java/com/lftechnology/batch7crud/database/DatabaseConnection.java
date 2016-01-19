package com.lftechnology.batch7crud.database;

import com.lftechnology.batch7crud.exception.DataException;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DatabaseConnection {

	public static Connection getConnection()
					throws DataException {

		try {

			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource dataSource = (DataSource) envContext.lookup(
							"jdbc/lf_testdatabase");
			Connection connection = dataSource.getConnection();
			return connection;

		}
		catch (NamingException ne) {
			ne.printStackTrace();
			throw new DataException();
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new DataException();
		}
	}
}
