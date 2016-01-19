package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.entity.Person;
import com.lftechnology.batch7crud.utils.DbConnection;

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


    public void add(Person person){
    }


    public void delete(Person person){
    }


    public void edit(Person person){
    }
}
