package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by romit on 1/14/16.
 */
@WebServlet(name = "EmployeeController", urlPatterns = {"/employees/*"})
public class EmployeeController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            list(request, response, 1);
        } else {
            String[] pathParts = pathInfo.split("/");
            if (pathParts[1].equals("create")) {
                createProcess(request, response);
            } else if (pathParts[2].equals("delete")) {
                int id = Integer.parseInt(pathParts[1]);
                deleteProcess(request, response, id);
            } else if (pathParts[2].equals("edit")) {
                int id = Integer.parseInt(pathParts[1]);
                editProcess(request, response, id);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            list(request, response, 1);
        } else {
            String[] pathParts = pathInfo.split("/");
            if (pathParts[1].equals("create")) {
                create(request, response);
            } else if (pathParts[2].equals("edit")) {
                int id = Integer.parseInt(pathParts[1]);
                edit(request, response, id);
            }
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response, int page) throws ServletException, IOException {
        try {
            EmployeeService employeeService = new EmployeeService();
            List<Employee> empList = employeeService.fetch();
            request.setAttribute("employeeList", empList);
            request.getRequestDispatcher("/WEB-INF/views/employees.jsp").forward(request, response);
        } catch (DataException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/form.jsp").forward(request, response);
    }

    private void createProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String contact = request.getParameter("contact");
        Employee employee = new Employee();
        employee.setName(name);
        employee.setAddress(address);
        employee.setEmail(email);
        employee.setContact(contact);

        try {
            EmployeeService employeeService = new EmployeeService();
            employeeService.insert(employee);
            response.sendRedirect(request.getContextPath()+"/employees");
        } catch (DataException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
        try {
            EmployeeService employeeService = new EmployeeService();
            request.setAttribute("employee", employeeService.fetch(id));
            request.getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(request, response);
        } catch (DataException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }

    private void editProcess(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String contact = request.getParameter("contact");
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setAddress(address);
        employee.setEmail(email);
        employee.setContact(contact);

        try {
            EmployeeService employeeService = new EmployeeService();
            employeeService.update(employee);
            response.sendRedirect(request.getContextPath()+"/employees");
        } catch (DataException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }

    private void deleteProcess(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
        try {
            EmployeeService employeeService = new EmployeeService();
            employeeService.delete(id);
        } catch (DataException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
        response.sendRedirect(request.getContextPath()+"/employees");
    }
}
