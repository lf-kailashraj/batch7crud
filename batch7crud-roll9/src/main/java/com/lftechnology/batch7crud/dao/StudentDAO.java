package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.entity.Student;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by sanjay on 1/14/16.
 */
public class StudentDAO {
    private Connection conn = null;
    public StudentDAO(){
       try {
           Context initContext = new InitialContext();
           Context envContext = (Context) initContext.lookup("java:/comp/env");
           DataSource ds = (DataSource) envContext.lookup("jdbc/db_userinfo_test");
           conn = ds.getConnection();
           System.out.println("connected");
       } catch (Exception ex) {
           System.out.println("in dao "+ex);

       }
    }
    public void insert(Student s)//get the object
    {
        try {
            PreparedStatement pstmt= conn.prepareStatement("INSERT INTO tbl_userinfo (firstname,middlename,lastname,address,grade) VALUES (?,?,?,?,?);");

            pstmt.setString(1, s.getFirstName());
            pstmt.setString(2, s.getMiddleName());
            pstmt.setString(3, s.getLastName());
            pstmt.setString(4, s.getAddress());
            pstmt.setInt(5, s.getGrade());
            System.out.println(pstmt.toString());

            pstmt.executeUpdate();
            System.out.println("Insert Success");
        } catch (Exception ex) {
            System.out.println("Insert Error - " + ex);
        }
    }
}
