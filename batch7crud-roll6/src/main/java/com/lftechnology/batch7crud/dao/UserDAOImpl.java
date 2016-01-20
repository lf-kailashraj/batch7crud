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

public class UserDAOImpl implements UserDAO {

    private static final Logger LOGGER = Logger.getLogger("Controller");
    Connection connection = null;

    public UserDAOImpl() {
        try {
            this.connection = DbConnector.getMySqlConnection();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }

    }

    @Override
    public void add(User user) throws DataException {
        String str = "insert into user (firstname,surname,username,password) values (?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(str);) {

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getSurName());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setString(4, user.getPassword());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DataException();
        }
    }

    @Override
    public void delete(int userID) throws DataException {
        String query = "delete from user where id= ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setLong(1, userID);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DataException(e.getMessage());
        }
    }

    @Override
    public List<User> fetch(int page, int limit) throws DataException {
        String query = "select * from user order by id  limit ? offset ?";
        List<User> userList = new ArrayList<>();
        int offset = (page - 1) * limit;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            ResultSet results = preparedStatement.executeQuery();

            while (results.next()) {
                User user = new User();

                user.setId(results.getInt("id"));
                user.setFirstName(results.getString("firstname"));
                user.setSurName(results.getString("surname"));
                user.setUserName(results.getString("username"));
                user.setPassword(results.getString("password"));

                userList.add(user);

            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DataException(e.getMessage());
        }
        return userList;

    }

    @Override
    public User fetchByID(int userID) throws DataException {
        User user = null;
        String query = "select * from user where id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setLong(1, userID);
            ResultSet results = preparedStatement.executeQuery();

            while (results.next()) {
                user = new User();

                user.setId(results.getInt("id"));
                user.setFirstName(results.getString("firstname"));
                user.setSurName(results.getString("surname"));
                user.setUserName(results.getString("username"));
                user.setPassword(results.getString("password"));
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DataException(e.getMessage());
        }
        return user;

    }

    @Override
    public void update(User user) throws DataException {
        String query = " update user set firstname = ?, surname = ?, username=? where id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getSurName());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setInt(4, user.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DataException(e.getMessage());
        }
    }

    @Override
    public int totalUser() {
        String query = "select count(*) from user ";
        int totalNumber = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                totalNumber = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        return totalNumber;
    }

}
