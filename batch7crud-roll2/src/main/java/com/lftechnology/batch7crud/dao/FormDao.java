package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.model.FormInformation;
import com.lftechnology.batch7crud.util.DBConnection;

import javax.activation.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by romit on 1/14/16.
 */
public class FormDao {
    public void insertFormData(FormInformation formInfo){
        try{
            Connection con = DBConnection.getPostgreSqlConnection();
            String qry = "INSERT INTO userinfo (username,address,email,contact) VALUES (?,?,?,?)";
            PreparedStatement preStmt = con.prepareStatement(qry);
            preStmt.setString(1, formInfo.getUserName());
            preStmt.setString(2, formInfo.getAddress());
            preStmt.setString(3, formInfo.getEmail());
            preStmt.setString(4, formInfo.getContact());
            preStmt.execute();

        }catch (Exception e){

        }
    }
    public List<FormInformation> getFormDataList(){
        try{

            Connection con = DBConnection.getPostgreSqlConnection();
            List<FormInformation> formDataList = new ArrayList<FormInformation>();
            String qry = "SELECT * from userinfo";
            PreparedStatement preStmt = con.prepareStatement(qry);

            ResultSet resultSet = preStmt.executeQuery();

            while(resultSet.next()){
                FormInformation formInformation = new FormInformation();

                formInformation.setUserId(resultSet.getInt("id"));
                formInformation.setUserName(resultSet.getString("username"));
                formInformation.setAddress(resultSet.getString("address"));
                formInformation.setEmail(resultSet.getString("email"));
                formInformation.setContact(resultSet.getString("contact"));

                formDataList.add(formInformation);
            }
            return formDataList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
