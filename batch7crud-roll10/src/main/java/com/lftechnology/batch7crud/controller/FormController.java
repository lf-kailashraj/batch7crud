package com.lftechnology.batch7crud.controller;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author binodnme
 * Created on 1/13/16
 */
@WebServlet("/add")
public class FormController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("fname");
        String lastName = req.getParameter("lname");

        Connection conn = null;

        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/lftest");
            conn = ds.getConnection();

        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


//        DbConnect dbConnect = new DbConnect();
//
//        try {
//            conn = dbConnect.getConnection();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        System.out.println("connection: "+ conn);
//        String query = "Insert INTO employee values("+2+",'"+firstName+"','"+lastName+"')";
        String query = "Insert INTO employee values('"+firstName+"','"+lastName+"')";
        System.out.println("query "+query);

        try {
            Statement stmt = conn.createStatement();

            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("fname: "+firstName+ " lname:"+lastName);
    }
}
