package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.service.EmployeeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by pratishshr on 1/14/16.
 */
@WebServlet(name = "EmployeeCreateController", urlPatterns = {"/employees/create"})
public class EmployeeCreateController extends HttpServlet {
    EmployeeService employeeService;

    public EmployeeCreateController() throws SQLException, ClassNotFoundException {
        employeeService = new EmployeeService();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "/WEB-INF/views/create.jsp";

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String station = request.getParameter("station");

        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setStation(station);

        try {
            employeeService.save(employee);
            response.sendRedirect("/employees");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
