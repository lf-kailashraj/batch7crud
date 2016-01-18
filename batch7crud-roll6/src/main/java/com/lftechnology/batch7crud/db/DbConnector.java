package com.lftechnology.batch7crud.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {

	private static String USERNAME = "root";
	private static String PASSWORD = "root";
	private static String MYSQL_DATABASE="jdbc:mysql://localhost/dbInitial";
	
	public static Connection getMySqlConnection(){
		Connection connection =null;
		try{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
			connection = DriverManager.getConnection(MYSQL_DATABASE,USERNAME,PASSWORD);
		}catch(SQLException e){
			System.out.println("Encountered sql exception"+e);
		}
		return connection;
	}
}
