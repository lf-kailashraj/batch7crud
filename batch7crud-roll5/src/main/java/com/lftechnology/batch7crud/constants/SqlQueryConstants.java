package com.lftechnology.batch7crud.constants;

public class SqlQueryConstants {
    public static final String INSERT_QUERY = "INSERT INTO employee (first_name, last_name, department, address) VALUES(?, ?, ?, ?)";
    public static final String DELETE_QUERY = "DELETE FROM employee WHERE id = ?";
    public static final String UPDATE_QUERY = "UPDATE employee SET first_name = ?, last_name = ?, department = ?, address = ? WHERE id = ?";
    public static final String READ_BY_ID_QUERY = "SELECT * FROM employee where id = ?";
    public static final String READ_ALL_QUERY = "SELECT * FROM employee LIMIT ? OFFSET ?";
    public static final String COUNT_QUERY = "SELECT COUNT(*) FROM employee";

    private SqlQueryConstants() {

    }

}
