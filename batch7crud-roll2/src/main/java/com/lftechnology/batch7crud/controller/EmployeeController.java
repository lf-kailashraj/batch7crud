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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by romit on 1/14/16.
 */
@WebServlet(name = "EmployeeController", urlPatterns = { "/employees/*" })

public class EmployeeController extends CommonHttpServlet {
    public static final Logger logger = Logger.getLogger(EmployeeController.class.getName());
    public static final String EMPLOYEE_PATH = "/employees";

    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (("/").equals(pathInfo) || pathInfo == null) {
            list(request, response);
        }

        String[] pathParts = pathInfo.split("/");
        if (("create").equals(pathParts[1])) {
            createProcess(request, response);
        } else if (("delete").equals(pathParts[2])) {
            try {
                int id = Integer.parseInt(pathParts[1]);
                deleteProcess(request, response, id);
            } catch (NumberFormatException e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
                displayPageNotFound(request, response);
            }
        } else if ("edit".equals(pathParts[2])) {
            try {
                int id = Integer.parseInt(pathParts[1]);
                editProcess(request, response, id);
            } catch (NumberFormatException e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
                displayPageNotFound(request, response);
            }
        }
    }

    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || ("/").equals(pathInfo)) {
            list(request, response);
        } else {
            String[] pathParts = pathInfo.split("/");
            if (("create").equals(pathParts[1])) {
                create(request, response);
            } else if (("edit").equals(pathParts[2])) {
                try {
                    int id = Integer.parseInt(pathParts[1]);
                    edit(request, response, id);
                } catch (NumberFormatException e) {
                    logger.log(Level.SEVERE, e.getMessage(), e);
                    displayPageNotFound(request, response);
                }
            }
        }
    }

    private int getPageNo(HttpServletRequest request) {
        try {
            if (request.getParameter("page") != null) {
                return Integer.parseInt(request.getParameter("page"));
            } else {
                return 1;
            }
        } catch (NumberFormatException e) {
            return 1;
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int page = getPageNo(request);
            EmployeeService employeeService = new EmployeeService();
            List<Employee> empList = employeeService.fetch(10, (page - 1) * 10);
            int totalNoOfRecords = employeeService.getTotalNoOfRecords();
            if (empList.isEmpty()) {
                displayPageNotFound(request, response);
            }
            int noOfPages = (int) Math.ceil(totalNoOfRecords * 1.0 / 10);
            request.setAttribute("employeeList", empList);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);
            request.getRequestDispatcher("/WEB-INF/views/employeesList.jsp").forward(request, response);
        } catch (DataException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            displayErrorPage(request, response, e.getMessage());
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            displayPageNotFound(request, response);
        }
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/employeeCreate.jsp").forward(request, response);
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
            response.sendRedirect(request.getContextPath() + EMPLOYEE_PATH);
        } catch (DataException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            displayErrorPage(request, response, e.getMessage());
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
        try {
            EmployeeService employeeService = new EmployeeService();
            request.setAttribute("employee", employeeService.fetchById(id));
            request.getRequestDispatcher("/WEB-INF/views/employeeEdit.jsp").forward(request, response);
        } catch (DataException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            displayErrorPage(request, response, e.getMessage());
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
            response.sendRedirect(request.getContextPath() + EMPLOYEE_PATH);
        } catch (DataException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            displayErrorPage(request, response, e.getMessage());
        }
    }

    private void deleteProcess(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
        try {
            EmployeeService employeeService = new EmployeeService();
            employeeService.delete(id);
        } catch (DataException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            displayErrorPage(request, response, e.getMessage());
        }
        response.sendRedirect(request.getContextPath() + EMPLOYEE_PATH);
    }
}
