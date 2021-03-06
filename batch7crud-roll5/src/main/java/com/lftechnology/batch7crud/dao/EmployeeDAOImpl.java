package com.lftechnology.batch7crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lftechnology.batch7crud.constants.SqlQueryConstants;
import com.lftechnology.batch7crud.db.DBConnection;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;

public class EmployeeDAOImpl {
    private static final Logger LOGGER = Logger.getLogger(EmployeeDAOImpl.class.getName());

    public List<Employee> fetch(int limit, int offSet) throws DataException {
        List<Employee> empList = new ArrayList<Employee>();

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(SqlQueryConstants.READ_ALL_QUERY)) { // NOSONAR
            ps.setInt(1, limit);
            ps.setInt(2, offSet);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                empList.add(setObjectAttribute(rs));
            }
            return empList;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DataException(e.getMessage());
        }
    }

    public Employee fetchById(int id) throws DataException {
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(SqlQueryConstants.READ_BY_ID_QUERY)) { // NOSONAR
            Employee emp = null;
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    emp = setObjectAttribute(rs);
                }
            }
            return emp;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DataException(e.getMessage());
        }
    }

    public void deleteById(int id) throws DataException {
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(SqlQueryConstants.DELETE_QUERY)) { // NOSONAR
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DataException(e.getMessage());
        }
    }

    public void create(Employee employee) throws DataException {
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(SqlQueryConstants.INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) { // NOSONAR
            setQueryAttribute(ps,employee).executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next())
                employee.setId(rs.getInt(1));

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DataException(e.getMessage());
        }
    }

    public void edit(Employee employee) throws DataException {
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(SqlQueryConstants.UPDATE_QUERY)) { // NOSONAR         
            setQueryAttribute(ps, employee).setInt(5, employee.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DataException(e.getMessage());
        }
    }

    public int count() throws DataException {
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(SqlQueryConstants.COUNT_QUERY); // NOSONAR
                ResultSet rs = ps.executeQuery()) {
            if (rs.next())
                return rs.getInt(1);
            else
                return 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DataException(e.getMessage());
        }
    }

    public Employee setObjectAttribute(ResultSet rs) throws SQLException {
        Employee emp = new Employee();
        emp.setId(rs.getInt("id"));
        emp.setFirstName(rs.getString("first_name"));
        emp.setLastName(rs.getString("last_name"));
        emp.setDepartment(rs.getString("department"));
        emp.setAddress(rs.getString("address"));
        return emp;
    }
    
    private PreparedStatement setQueryAttribute(PreparedStatement ps, Employee employee) throws SQLException {
        ps.setString(1, employee.getFirstName());
        ps.setString(2, employee.getLastName());
        ps.setString(3, employee.getDepartment());
        ps.setString(4, employee.getAddress());
        return ps;
    }
}
