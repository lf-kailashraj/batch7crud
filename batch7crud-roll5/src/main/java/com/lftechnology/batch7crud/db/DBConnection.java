/**
 * 
 */
package com.lftechnology.batch7crud.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.lftechnology.batch7crud.exception.DataException;



/**
 * @author leapfrog
 *
 */
public class DBConnection {
	
	public static Connection getConnection() throws DataException{
		try{
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/EmployeeManagement");
			Connection conn = ds.getConnection();
			return conn;
		}catch(SQLException e){
			throw new DataException();
		}catch(NamingException e){
			throw new DataException();
		}
		
	}
}
