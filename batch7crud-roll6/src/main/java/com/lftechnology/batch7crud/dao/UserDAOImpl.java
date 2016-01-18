package com.lftechnology.batch7crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lftechnology.batch7crud.db.DbConnector;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.User;

public class UserDAOImpl implements UserDAO {

    Connection connection = null;

    public UserDAOImpl() {
        this.connection = DbConnector.getMySqlConnection();

    }

    public void add(User user) throws DataException {
        try {
            String str = "insert into user (firstname,surname,username,password) values (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(str);

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getSurName());
            statement.setString(3, user.getUserName());
            statement.setString(4, user.getPassword());

            statement.execute();
            System.out.println("user added");
        } catch (SQLException e) {
            System.err.println(e);
            throw new DataException();
        }
    }

    public void deleteUser(int userID) throws DataException {
        try {
            String query = "delete from user where id= ?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, userID);
            statement.executeUpdate();

            System.out.println("user deleted");
        } catch (SQLException e) {
            System.err.println(e);
            throw new DataException();
        }
    }

    public List<User> fetch(int page) throws DataException {
        String str = "select * from user";

        List<User> userList = new ArrayList<User>();
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(str);
            ResultSet results = statement.executeQuery();

            User user = null;

            while (results.next()) {
                user = new User();

                user.setId(results.getString("id"));
                user.setFirstName(results.getString("firstname"));
                user.setSurName(results.getString("surname"));
                user.setUserName(results.getString("username"));
                user.setPassword(results.getString("password"));

                userList.add(user);

            }
            return userList;

        } catch (SQLException e) {
            System.err.println(e);
            throw new DataException();
        }

    }

    public void delete(int userID) throws DataException {
        // TODO Auto-generated method stub

    }

    public User fetchByID(int userID) throws DataException {
        // TODO Auto-generated method stub
        return null;
    }

}
