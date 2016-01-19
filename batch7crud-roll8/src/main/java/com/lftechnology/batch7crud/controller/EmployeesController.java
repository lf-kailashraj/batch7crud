package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.model.User;
import com.lftechnology.batch7crud.services.EmployeeServices;
import com.lftechnology.batch7crud.services.UserServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by grishma on 1/19/16.
 */
@WebServlet({ "/employees/*" })
public class EmployeesController extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path == null) {
            request.getServletContext().getRequestDispatcher("/WEB-INF/views/employees/index.jsp").forward(request, response);
        }
        else {
            String[] parts = path.split("/");
            if (parts[1].equals("create")) {
                create(request, response);}
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getPathInfo();

        if (path == null) {
            request.getServletContext().getRequestDispatcher("/WEB-INF/views/employees/index.jsp").forward(request, response);
        }
        else {
            String[] parts = path.split("/");
            if (parts[1].equals("createProcess")) {
                createProcess(request, response);
            }
        }
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/WEB-INF/views/employees/new.jsp").forward(request, response);
    }

    private void createProcess(HttpServletRequest request, HttpServletResponse response) {
        try {
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String designation = request.getParameter("designation");
            String phone = request.getParameter("phone");
            EmployeeServices employeeServices = new EmployeeServices();

            Employee employee = new Employee();
            employee.setName(name);
            employee.setAddress(address);
            employee.setDesignation(designation);
            employee.setPhone(phone);

            employeeServices.create(employee);
            fetch(request, response);
        } catch (DataException e) {
            e.printStackTrace();
            // check here for error and do required redirection and message display
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fetch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/employees");
    }
}
