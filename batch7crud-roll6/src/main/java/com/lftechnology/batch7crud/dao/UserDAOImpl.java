package com.lftechnology.batch7crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lftechnology.batch7crud.db.DbConnector;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.User;
import com.lftechnology.batch7crud.util.TypeCaster;

public class UserDAOImpl implements UserDAO {

    private static final Logger logger = Logger.getLogger("Controller");
    Connection connection = null;

    public UserDAOImpl() {
        try {
            this.connection = DbConnector.getMySqlConnection();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }

    }

    @Override
    public void add(User user) throws DataException {
        String str = "insert into user (firstname,surname,username,password) values (?,?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(str);) {

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getSurName());
            statement.setString(3, user.getUserName());
            statement.setString(4, user.getPassword());

            statement.execute();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
            throw new DataException();
        }
    }

    @Override
    public void delete(int userID) throws DataException {
        String query = "delete from user where id= ?";

        try (PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setLong(1, userID);
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
            throw new DataException(e.getMessage());
        }
    }

    @Override
    public List<User> fetch(int page) throws DataException {
        String query = "select * from user";
        List<User> userList = new ArrayList<User>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                User user = new User();

                user.setId(TypeCaster.toInt(results.getString("id")));
                user.setFirstName(results.getString("firstname"));
                user.setSurName(results.getString("surname"));
                user.setUserName(results.getString("username"));
                user.setPassword(results.getString("password"));

                userList.add(user);

            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
            throw new DataException(e.getMessage());
        }
        return userList;

    }

    @Override
    public User fetchByID(int userID) throws DataException {
        User user = null;
        String query = "select * from user where id=?";

        try (PreparedStatement statement = connection.prepareStatement(query);) {
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
            logger.log(Level.SEVERE, e.getMessage());
            throw new DataException(e.getMessage());
        }
        return user;

    }

    @Override
    public void update(User user) throws DataException {
        String query = " update user set firstname = ?, surname = ?, username=? where id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query);) {

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getSurName());
            statement.setString(3, user.getUserName());
            statement.setInt(4, user.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
            throw new DataException(e.getMessage());
        }
    }

}
