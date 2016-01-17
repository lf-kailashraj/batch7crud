package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.entity.Person;
import com.lftechnology.batch7crud.entity.Student;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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


    public List<Student> fetchData(Integer totalDataNum){
        List<Student> studentList = new ArrayList<Student>();
        try {
            conn = getConnection();
            System.out.println("connected");
            stmt = conn.createStatement();

            String query = "select * from student limit"+totalDataNum;
            ResultSet resultSet = stmt.executeQuery(query);

            while(resultSet.next()){
                Integer id = resultSet.getInt("id");
                Integer rollNo = resultSet.getInt("roll");
                String department = resultSet.getString("department");
                String batch = resultSet.getString("batch");
                Integer personId = resultSet.getInt("person_id");

                String query1 = "select * from person where id="+personId;
                Statement statement = conn.createStatement();
                ResultSet resultSet1 = statement.executeQuery(query1);

                resultSet1.next();
                Integer personID = resultSet1.getInt("id");
                String name = resultSet1.getString("name");
                String address = resultSet1.getString("address");
                Date dob = resultSet1.getDate("dob");

                Person person = new Person();
                person.setId(personID);
                person.setName(name);
                person.setAddress(address);
                person.setDob(dob);

                Student student = new Student();
                student.setId(id);
                student.setRoll(rollNo);
                student.setDepartment(department);
                student.setBatch(batch);
                student.setPerson(person);

                studentList.add(student);
            }

        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentList;
    }


    public Boolean add(Student stud){
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            String query = "insert into student (person_id,department,batch,roll) values("+
                    stud.getPerson().getId()+",'"+stud.getDepartment()+"','"+stud.getBatch()+"',"+
                    stud.getRoll()+")";

            System.out.println("student dao query: "+query);
            stmt.executeUpdate(query);

            ResultSet resultSet = stmt.executeQuery("SELECT currval('student_id_seq')");
            resultSet.next();
            Integer id = resultSet.getInt("currval");
            stud.setId(id);
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return false;
    }


    public void delete(Student student){

    }


    public void edit(Student student){

    }


    private Connection getConnection() throws NamingException, SQLException {
        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:/comp/env");
        DataSource ds = (DataSource) envContext.lookup("jdbc/library");
        return ds.getConnection();
    }
}
