package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.entity.Person;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @Author binodnme
 * Created on 1/17/16
 */
public class PersonDao {
    private Connection conn = null;
    private Statement stmt = null;


    public Boolean add(Person person){
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
            String date = dateFormat.format(person.getDob());

            String query = "insert into person (name,address,dob) values('"+ person.getName()+"','"+
                    person.getAddress()+"','"+ date+"')";

            System.out.println("person dao query: "+query);

            stmt.executeUpdate(query);

            ResultSet resultSet = stmt.executeQuery("SELECT currval('person_id_seq')");
            resultSet.next();
            Integer id = resultSet.getInt("currval");
            person.setId(id);
            System.out.println("person dao id: "+ person.getId());
            conn.commit();
            return true;

        } catch (SQLException e) {
//            conn.rollback();
            System.out.println("exception here");
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        return false;
    }


    public Person delete(Person person){
        try {
            conn = getConnection();
            String query = "delete from person where id="+ person.getId();
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            return person;
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }


    public void edit(Person person){

    }


    private Connection getConnection() throws NamingException, SQLException {
        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:/comp/env");
        DataSource ds = (DataSource) envContext.lookup("jdbc/library");
        return ds.getConnection();
    }
}
