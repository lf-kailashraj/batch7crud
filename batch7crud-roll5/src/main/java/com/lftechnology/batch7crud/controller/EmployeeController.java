package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constants.EmployeeAttributeConstants;
import com.lftechnology.batch7crud.constants.NormalConstants;
import com.lftechnology.batch7crud.constants.UrlConstants;
import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.services.EmployeeServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/employees/*")
public class EmployeeController extends CustomHttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(EmployeeController.class.getName());
    private static EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path != null) {
            String[] pathParts = path.split("/");

            if (pathParts.length == 2 && NormalConstants.CREATE.equals(pathParts[1]))
                create(request, response);

            else if (pathParts.length == 3 && NormalConstants.EDIT.equals(pathParts[2])) {
                edit(request, response);
            }

            else
                show404(request, response);
        }

        else {
            fetchData(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();

        if (path != null) {
            String[] pathParts = path.split("/");

            if (NormalConstants.CREATE_PROCESS.equals(pathParts[1])) {
                createProcess(request, response);
            }

            else if (NormalConstants.EDIT_PROCESS.equals(pathParts[2])) {
                editProcess(request, response);
            }

            else if (NormalConstants.DELETE_PROCESS.equals(pathParts[2])) {
                deleteProcess(request, response);
            }

        } else {
            fetchData(request, response);
        }

    }

    private void deleteProcess(HttpServletRequest request, HttpServletResponse response) {
        try {
            int employeeId = parameterValueAsInt(request, 2);
            employeeService.deleteById(employeeId);

            response.sendRedirect(UrlConstants.EMPLOYEE_LIST_URL);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            show500(request, response, e);
        }

    }

    private void edit(HttpServletRequest request, HttpServletResponse response) {
        try {
            int employeeId = parameterValueAsInt(request, 2);
            request.setAttribute(NormalConstants.EMPLOYEE, employeeService.fetchById(employeeId));
            request.getRequestDispatcher(UrlConstants.EMPLOYEE_EDIT_URL).forward(request, response);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            show500(request, response, e);
        }
    }

    private void editProcess(HttpServletRequest request, HttpServletResponse response) {
        Employee emp = new Employee();
        try {
            int employeeId = parameterValueAsInt(request, 2);
            emp = setDataAttribute(request);
            emp.setId(employeeId);

            employeeService.edit(emp);

            response.sendRedirect(UrlConstants.EMPLOYEE_LIST_URL);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            show500(request, response, e);
        }

    }

    private void create(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher(UrlConstants.EMPLOYEE_CREATE_URL).forward(request, response);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            show500(request, response, e);
        }

    }

    private void createProcess(HttpServletRequest request, HttpServletResponse response) {
        try {
            employeeService.create(setDataAttribute(request));

            response.sendRedirect(UrlConstants.EMPLOYEE_LIST_URL);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            show500(request, response, e);
        }

    }

    private void fetchData(HttpServletRequest request, HttpServletResponse response) {
        List<Employee> employeeList = new ArrayList<Employee>();
        try {
            int employeeCount = employeeService.count();
            int pageNo = pageNumber(request);
            employeeList = employeeService.fetch(10, (pageNo - 1) * 10);

            request.setAttribute(NormalConstants.EMPLOYEE_LIST, employeeList);
            request.setAttribute(NormalConstants.PAGE_NO, pageNo);
            request.setAttribute(NormalConstants.NO_OF_EMPLOYEES, employeeCount);
            request.setAttribute(NormalConstants.NO_EMP_IN_PAGE, 10);

            request.getRequestDispatcher(UrlConstants.EMPLOYEE_FETCH_URL).forward(request, response);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            show500(request, response, e);
        }

    }

    private Employee setDataAttribute(HttpServletRequest request) {
        Employee emp = new Employee();
        emp.setFirstName(request.getParameter(EmployeeAttributeConstants.FIRST_NAME));
        emp.setLastName(request.getParameter(EmployeeAttributeConstants.LAST_NAME));
        emp.setDepartment(request.getParameter(EmployeeAttributeConstants.DEPARTMENT));
        emp.setAddress(request.getParameter(EmployeeAttributeConstants.ADDRESS));

        return emp;
    }

}
