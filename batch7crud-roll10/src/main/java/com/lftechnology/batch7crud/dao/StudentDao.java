package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.utils.DbConnection;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author binodnme
 * Created on 1/14/16
 */
public class StudentDao {

    public List<Student> fetch(Integer page) throws DataException {
        List<Student> studentList = new ArrayList<Student>();
        try {
            Connection conn = DbConnection.getConnection();

            Integer totalDataToFetch = 30;
            Integer offset = (page-1)*totalDataToFetch;
            String query = "select * from student limit "+totalDataToFetch+" Offset "+offset;
//            String query = "select * from student ";
//            String query = "select * from student limit ? offset ?";
//            PreparedStatement ps = conn.prepareStatement(query);
//            ps.setInt(1,totalDataToFetch);
//            ps.setInt(2,offset);
            Statement stmt = conn.createStatement();
            ResultSet studentResult = stmt.executeQuery(query);

            while(studentResult.next()){
                Integer id = studentResult.getInt("id");
                Integer rollNo = studentResult.getInt("roll");
                String department = studentResult.getString("department");
                String batch = studentResult.getString("batch");
                String name = studentResult.getString("name");
                String address = studentResult.getString("address");
                Date dob = studentResult.getDate("dob");

                Student student = new Student();
                student.setId(id);
                student.setRoll(rollNo);
                student.setDepartment(department);
                student.setBatch(batch);
                student.setName(name);
                student.setAddress(address);
                student.setDob(dob);

                studentList.add(student);
            }

            conn.close();
            return studentList;

        } catch (NamingException e) {
            e.printStackTrace();
            throw new DataException(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataException(e.getMessage());
        }
    }


    public void insert(Student stud) throws DataException {
        try {
            Connection conn = DbConnection.getConnection();

            String studentQuery = "insert into student (name,address,dob,department,batch,roll) values(?,?,?,?,?,?)";
            PreparedStatement ps1 = conn.prepareStatement(studentQuery);
            ps1.setString(1,stud.getName());
            ps1.setString(2,stud.getAddress());
            ps1.setDate(3, new java.sql.Date(stud.getDob().getTime()));
            ps1.setString(4,stud.getDepartment());
            ps1.setString(5,stud.getBatch());
            ps1.setInt(6, stud.getRoll());
            ps1.executeUpdate();

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataException(e.getMessage());
        } catch (NamingException e) {
            e.printStackTrace();
            throw new DataException(e.getMessage());
        }

    }


    public void delete(Integer studentId) throws DataException {
        try {
            Connection conn = DbConnection.getConnection();

            String query = "delete from student where id=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,studentId);
            ps.executeUpdate();

        } catch (NamingException e) {
            e.printStackTrace();
            throw new DataException(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataException(e.getMessage());
        }
    }


    public void update(Student stud) throws DataException {
        //update the student record
        try {
            Connection conn = DbConnection.getConnection();

            String studentQuery = "UPDATE student set name=?, address=?, dob=?, department=?, batch=?, roll=? WHERE id=?";
            PreparedStatement ps1 = conn.prepareStatement(studentQuery);
            ps1.setString(1,stud.getName());
            ps1.setString(2,stud.getAddress());
            ps1.setDate(3, new java.sql.Date(stud.getDob().getTime()));
            ps1.setString(4,stud.getDepartment());
            ps1.setString(5,stud.getBatch());
            ps1.setInt(6,stud.getRoll());
            ps1.setInt(7,stud.getId());
            ps1.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataException(e.getMessage());
        } catch (NamingException e) {
            e.printStackTrace();
            throw new DataException(e.getMessage());
        }
    }


    public Student fetchById(Integer id) throws DataException {
        try {
            String query = "select * from student where id=?";
            Connection conn = DbConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,id);

            ResultSet studentResult = ps.executeQuery();

            if (studentResult.next()){
                Student student = new Student();
                student.setId(studentResult.getInt("id"));
                student.setRoll(studentResult.getInt("roll"));
                student.setDepartment(studentResult.getString("department"));
                student.setBatch(studentResult.getString("batch"));

                student.setName(studentResult.getString("name"));
                student.setAddress(studentResult.getString("address"));
                student.setDob(studentResult.getDate("dob"));

                return student;
            }else{
                return null;
            }

        } catch (NamingException e) {
            e.printStackTrace();
            throw new DataException(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataException(e.getMessage());
        }

    }
}
