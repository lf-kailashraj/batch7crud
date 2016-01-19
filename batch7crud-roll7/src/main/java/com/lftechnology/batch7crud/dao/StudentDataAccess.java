package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.db.DbUtilities;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Student;

import javax.naming.NamingException;
import javax.xml.crypto.Data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by leapfrog on 1/18/16.
 */
public class StudentDataAccess {
    public void addNew(Student s) throws DataException{
        try{
            String query = "insert into students (name,address,roll) values (?,?,?);";
            PreparedStatement ps = DbUtilities.getPreparedStatement(query);
            ps.setString(1, s.getName());
            ps.setString(2, s.getAddress());
            ps.setInt(3,s.getRoll());
            ps.executeUpdate();
        }
        catch (SQLException ex){
            System.out.println("SQLException");
        }
    }

    public List<Student> fetch(int page) throws DataException{
        try{
            List<Student> studentList = new ArrayList<Student>();
            ResultSet rs = DbUtilities.getPreparedStatement("select * from students").executeQuery();
            while(rs.next()){
                Student s = new Student(rs.getString(1),rs.getString(2),rs.getInt(3));
                studentList.add(s);
            }
            return studentList;

        }catch(SQLException ex){
            throw new DataException();
        }
    }

    public Student fetchById(int roll) throws DataException{
        try{
            String query = "select * from students where roll = ?";
            PreparedStatement ps = DbUtilities.getPreparedStatement(query);
            ps.setInt(1,roll);
            ResultSet rs = ps.executeQuery();

            Student s = null;
            while(rs.next()){
                s = new Student();
                s.setName(rs.getString("name"));
                s.setAddress(rs.getString("address"));
                s.setRoll(Integer.parseInt(rs.getString("roll")));
            }
            return s;

        }catch(SQLException ex){
            throw new DataException(ex.getMessage());
        }
    }

    public void update(Student s) throws DataException{
        try{
            String query = "update students set name = ?,address = ? where roll = ?";
            PreparedStatement ps = DbUtilities.getPreparedStatement(query);
            ps.setString(1, s.getName());
            ps.setString(2, s.getAddress());
            ps.setInt(3,s.getRoll());
            ps.executeUpdate();
        }
        catch (SQLException ex){
            System.out.println("SQLException");
        }
    }

}