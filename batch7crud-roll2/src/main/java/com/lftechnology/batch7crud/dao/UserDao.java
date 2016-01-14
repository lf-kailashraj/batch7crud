package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.model.User;
import com.lftechnology.batch7crud.util.DBConnection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by romit on 1/14/16.
 */
public class UserDao{

    public User getUser(User user) throws SQLException,NamingException{
        User user1 = null;
        Connection con = DBConnection.getPostgreSqlConnection();
        String qry = "SELECT * FROM admin WHERE username=? AND password=?";
        PreparedStatement preStmt = con.prepareStatement(qry);
        preStmt.setString(1,user.getUserName());
        preStmt.setString(2, user.getPassword());

        ResultSet resultSet = preStmt.executeQuery();
        user1 = new User();
        while (resultSet.next()){
            user1.setId(resultSet.getInt("id"));
            user1.setUserName(resultSet.getString("username"));
            user1.setPassword(resultSet.getString("password"));
        }
        return user1;
    }
}
