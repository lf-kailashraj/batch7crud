package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.db.DbUtilities;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by leapfrog on 1/18/16.
 */
public class StudentDataAccess {
    private static final Logger LOGGER = Logger.getLogger("StudentDataAccessLogger");
    PreparedStatement ps = null;
    Connection conn = null;
    public void addNew(Student s) throws DataException{
        try{
            String query = "insert into students (name,address,roll) values (?,?,?);";
            conn = DbUtilities.getConncetion();
            ps = conn.prepareStatement(query);
            ps.setString(1, s.getName());
            ps.setString(2, s.getAddress());
            ps.setInt(3, s.getRoll());
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException ex){
            LOGGER.log(Level.SEVERE, ex.getMessage(),ex);
        }
    }

    public List<Student> fetch(int page) throws DataException{
        try{
            List<Student> studentList = new ArrayList<Student>();
            String query = "select * from students";
            conn = DbUtilities.getConncetion();
            ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Student s = new Student(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));
                studentList.add(s);
            }
            rs.close();
            ps.close();
            return studentList;

        }catch(SQLException ex){
            LOGGER.log(Level.SEVERE,ex.getMessage(),ex);
            throw new DataException();
        }
    }

    public Student fetchById(int id) throws DataException{
        try{
            String query = "select * from students where id = ?";
            conn = DbUtilities.getConncetion();
            ps = conn.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            Student s = null;
            while(rs.next()){
                s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setAddress(rs.getString("address"));
                s.setRoll(rs.getInt("roll"));
            }
            rs.close();
            ps.close();
            return s;

        }catch(SQLException ex){
            LOGGER.log(Level.SEVERE,ex.getMessage(),ex);
            throw new DataException();
        }
    }

    public void update(Student s,int id) throws DataException{
        try{
            String query = "update students set name = ?, address = ?, roll = ? where id = ?";
            conn = DbUtilities.getConncetion();
            ps = conn.prepareStatement(query);
            ps.setString(1, s.getName());
            ps.setString(2, s.getAddress());
            ps.setInt(3, s.getRoll());
            ps.setInt(4,id);
            System.out.println("ID"+id);
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException ex){
            LOGGER.log(Level.SEVERE, ex.getMessage(),ex);
        }
    }

    public void delete(int roll) throws DataException{
        try{
            String query = "delete from students where roll = ?";
            conn = DbUtilities.getConncetion();
            ps = conn.prepareStatement(query);
            ps.setInt(1,roll);
            ps.executeUpdate();
            ps.close();

        }catch(SQLException ex){
            LOGGER.log(Level.SEVERE,ex.getMessage(),ex);
            throw new DataException();
        }
    }

}
