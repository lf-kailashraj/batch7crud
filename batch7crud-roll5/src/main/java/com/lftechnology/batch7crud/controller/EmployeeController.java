package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constants.EmployeeAttributeConstants;
import com.lftechnology.batch7crud.constants.NormalConstants;
import com.lftechnology.batch7crud.constants.UrlConstants;
import com.lftechnology.batch7crud.exception.ValidationExceptions;
import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.services.EmployeeServiceImpl;
import com.lftechnology.batch7crud.validator.EmployeeValidator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
    private EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
    private EmployeeValidator employeeValidator = new EmployeeValidator();

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
            request.setAttribute(EmployeeAttributeConstants.ID, employeeId);
            request.getRequestDispatcher(UrlConstants.EMPLOYEE_EDIT_URL).forward(request, response);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            show500(request, response, e);
        }
    }

    private void editProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Employee emp = new Employee();
        try {
            int employeeId = parameterValueAsInt(request, 2);
            emp = employeeValidator.createObject(setDataAttribute(request));
            emp.setId(employeeId);
            employeeService.edit(emp);
            response.sendRedirect(UrlConstants.EMPLOYEE_LIST_URL);
        } catch (ValidationExceptions e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);   
            request.setAttribute("employeeId", emp.getId());
            request.setAttribute(NormalConstants.MESSAGE, e.getErrorMessage());
            request.getRequestDispatcher(UrlConstants.EMPLOYEE_EDIT_URL).forward(request, response);
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

    private void createProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            employeeService.create(employeeValidator.createObject(setDataAttribute(request)));
            response.sendRedirect(UrlConstants.EMPLOYEE_LIST_URL);
        } catch (ValidationExceptions e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            request.setAttribute(NormalConstants.MESSAGE, e.getErrorMessage());
            request.getRequestDispatcher(UrlConstants.EMPLOYEE_CREATE_URL).forward(request, response);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            show500(request, response, e);
        }

    }

    private void fetchData(HttpServletRequest request, HttpServletResponse response) {
        List<Employee> employeeList = new ArrayList<Employee>();
        try {
            Double employeeCount = (double) employeeService.count();
            int pageNo = pageNumber(request);
            employeeList = employeeService.fetch(10, (pageNo - 1) * 10);

            request.setAttribute(NormalConstants.EMPLOYEE_LIST, employeeList);
            request.setAttribute(NormalConstants.PAGE_NO, pageNo);
            request.setAttribute(NormalConstants.NO_OF_EMPLOYEES, employeeCount);
            request.setAttribute(NormalConstants.NO_RECORDS_IN_PAGE, 10);
            request.setAttribute("pageLink", Math.ceil(employeeCount / 10));
            request.getRequestDispatcher(UrlConstants.EMPLOYEE_FETCH_URL).forward(request, response);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            show500(request, response, e);
        }

    }

    private HashMap<String, String> setDataAttribute(HttpServletRequest request) {
        HashMap<String, String> input = new HashMap<String, String>();
        input.put(EmployeeAttributeConstants.FIRST_NAME, request.getParameter(EmployeeAttributeConstants.FIRST_NAME));
        input.put(EmployeeAttributeConstants.LAST_NAME, request.getParameter(EmployeeAttributeConstants.LAST_NAME));
        input.put(EmployeeAttributeConstants.PASSWORD, request.getParameter(EmployeeAttributeConstants.PASSWORD));
        input.put(EmployeeAttributeConstants.DEPARTMENT, request.getParameter(EmployeeAttributeConstants.DEPARTMENT));
        input.put(EmployeeAttributeConstants.ADDRESS, request.getParameter(EmployeeAttributeConstants.ADDRESS));
        return input;
    }

}
