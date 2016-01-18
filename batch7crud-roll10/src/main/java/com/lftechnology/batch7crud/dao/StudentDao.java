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
    private Connection conn = null;
    private Statement stmt = null;
    private PreparedStatement ps = null;


    public List<Student> fetch(Integer pageNum) throws DataException {
        List<Student> studentList = new ArrayList<Student>();
        try {
            conn = DbConnection.getConnection();
            stmt = conn.createStatement();

            String query = "select * from student";
            ResultSet studentResult = stmt.executeQuery(query);

            while(studentResult.next()){
                Integer id = studentResult.getInt("id");
                Integer rollNo = studentResult.getInt("roll");
                String department = studentResult.getString("department");
                String batch = studentResult.getString("batch");

                String query1 = "select * from person where id="+id;
                Statement statement = conn.createStatement();
                ResultSet personResult = statement.executeQuery(query1);

                while (personResult.next()){
                    String name = personResult.getString("name");
                    String address = personResult.getString("address");
                    Date dob = personResult.getDate("dob");

                    Student student = new Student();
                    student.setId(id);
                    student.setRoll(rollNo);
                    student.setDepartment(department);
                    student.setBatch(batch);

                    student.setName(name);
                    student.setAddress(address);
                    student.setDob(dob);

                    studentList.add(student);
                    break;
                }

            }

        } catch (NamingException e) {
            e.printStackTrace();
            throw new DataException();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataException();
        }

        return studentList;
    }


    public void insert(Student stud) throws DataException {
        try {
            conn = DbConnection.getConnection();

            conn.setAutoCommit(false);

            String personQuery = "insert into person(name, address, dob) values(?,?,?)";

            ps = conn.prepareStatement(personQuery);
            ps.setString(1,stud.getName());
            ps.setString(2,stud.getAddress());
            ps.setDate(3, new java.sql.Date(stud.getDob().getTime()));
            ps.executeUpdate();

//            ResultSet resultSet = stmt.executeQuery("SELECT currval('person_id_seq')");
            ResultSet resultSet = stmt.executeQuery("SELECT last_value FROM person_id_seq");
            resultSet.next();
            Integer id = resultSet.getInt("last_value");
            stud.setId(id);

            String studentQuery = "insert into student (id,department,batch,roll) values(?,?,?,?)";
            PreparedStatement ps1 = conn.prepareStatement(studentQuery);
            ps1.setInt(1,stud.getId());
            ps1.setString(2,stud.getDepartment());
            ps1.setString(3,stud.getBatch());
            ps1.setInt(4,stud.getRoll());
            ps1.executeUpdate();

            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
                throw new DataException("exception on rolling back");
            }
            throw new DataException("data insertion error");
        } catch (NamingException e) {
            e.printStackTrace();
            throw new DataException("data insertion error");
        }

    }


    public void delete(Student student){
        //care transaction
    }


    public void update(Student stud) throws DataException {
        //update the student record
        try {
            conn = DbConnection.getConnection();

            conn.setAutoCommit(false);

            String personQuery = "UPDATE person SET name=?, address=?, dob=? where id="+stud.getId();

            ps = conn.prepareStatement(personQuery);
            ps.setString(1,stud.getName());
            ps.setString(2,stud.getAddress());
            ps.setDate(3, new java.sql.Date(stud.getDob().getTime()));
            ps.executeUpdate();


//            String studentQuery = "insert into student (id,department,batch,roll) values(?,?,?,?)";
            String studentQuery = "UPDATE student set department=?, batch=?, roll=? WHERE id="+stud.getId();
            PreparedStatement ps1 = conn.prepareStatement(studentQuery);
            ps1.setString(1,stud.getDepartment());
            ps1.setString(2,stud.getBatch());
            ps1.setInt(3,stud.getRoll());
            ps1.executeUpdate();

            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
                throw new DataException("exception on rolling back");
            }
            throw new DataException("data insertion error");
        } catch (NamingException e) {
            e.printStackTrace();
            throw new DataException("data insertion error");
        }
    }


    public Student getStudentById(Integer id) throws DataException {
        try {
            String query = "select * from student where id="+id;
            Connection conn = DbConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet studentResult = stmt.executeQuery(query);

            while (studentResult.next()){
                Student student = new Student();
                student.setId(studentResult.getInt("id"));
                student.setRoll(studentResult.getInt("roll"));
                student.setDepartment(studentResult.getString("department"));
                student.setBatch(studentResult.getString("batch"));

                String personQuery = "select * from person where id="+id;
                ResultSet personResult = stmt.executeQuery(personQuery);
                personResult.next();

                student.setName(personResult.getString("name"));
                student.setAddress(personResult.getString("address"));
                student.setDob(personResult.getDate("dob"));

                return student;
            }

        } catch (NamingException e) {
            e.printStackTrace();
            throw new DataException("exception on getting student data by id");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataException("exception on getting student data by id");
        }
        return null;
    }
}
