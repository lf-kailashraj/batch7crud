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
import com.lftechnology.batch7crud.util.TypeCaster;

public class UserDAOImpl implements UserDAO {

    Connection connection = null;

    public UserDAOImpl() {
        try {
            this.connection = DbConnector.getMySqlConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

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

    public void delete(int userID) throws DataException {
        try {
            String query = "delete from user where id= ?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, userID);
            statement.executeUpdate();

            System.out.println("user deleted");
        } catch (SQLException e) {
            System.err.println(e);
            throw new DataException(e.getMessage());
        }
    }

    public List<User> fetch(int page) throws DataException {
        String query = "select * from user";

        List<User> userList = new ArrayList<User>();
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(query);
            ResultSet results = statement.executeQuery();

            User user = null;

            while (results.next()) {
                user = new User();

                user.setId(TypeCaster.toInt(results.getString("id")));
                user.setFirstName(results.getString("firstname"));
                user.setSurName(results.getString("surname"));
                user.setUserName(results.getString("username"));
                user.setPassword(results.getString("password"));

                userList.add(user);

            }

        } catch (SQLException e) {
            System.err.println(e);
            throw new DataException(e.getMessage());
        }
        return userList;

    }

    public User fetchByID(int userID) throws DataException {
        User user = null;

        try {
            String query = "select * from user where id=?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, userID);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                user = new User();

                user.setId(TypeCaster.toInt(results.getString("id")));
                user.setFirstName(results.getString("firstname"));
                user.setSurName(results.getString("surname"));
                user.setUserName(results.getString("username"));
                user.setPassword(results.getString("password"));
            }

        } catch (SQLException e) {
            System.err.println(e);
            throw new DataException(e.getMessage());
        }
        return user;

    }

    public void update(User user) throws DataException {

        try {
            String query = " update user set firstname = ?, surname = ?, username=? where id = ?";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getSurName());
            statement.setString(3, user.getUserName());
            statement.setInt(4, user.getId());

            statement.executeUpdate();
            System.out.println("User updated");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataException(e.getMessage());
        }
    }

}
