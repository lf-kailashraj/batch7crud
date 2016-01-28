package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constants.EmployeeAttributeConstants;
import com.lftechnology.batch7crud.constants.NormalConstants;
import com.lftechnology.batch7crud.constants.UrlConstants;
import com.lftechnology.batch7crud.exception.ValidationExceptions;
import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.services.EmployeeServiceImpl;
import com.lftechnology.batch7crud.util.EmployeeFactory;

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
    private EmployeeFactory employeeFactory = new EmployeeFactory();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        actionByURLForGet(request, response);
    }

    private void actionByURLForGet(HttpServletRequest request, HttpServletResponse response) {
        String action = getAction(request);
        switch (action) {
        case NormalConstants.LIST:
            fetchData(request, response);
            break;

        case NormalConstants.EDIT:
            edit(request, response);
            break;

        case NormalConstants.CREATE:
            create(request, response);
            break;

        default:
            show404(request, response);
            break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = getAction(request);
        switch (action) {
        case NormalConstants.EDIT:
            editProcess(request, response);
            break;

        case NormalConstants.CREATE:
            createProcess(request, response);
            break;

        case NormalConstants.DELETE_PROCESS:
            deleteProcess(request, response);
            break;

        default:
            show404(request, response);
            break;
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

    private void editProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Employee emp = new Employee();
        try {
            int employeeId = parameterValueAsInt(request, 2);
            emp = employeeFactory.createObjects(setDataAttribute(request));
            emp.setId(employeeId);
            employeeService.edit(emp);
            response.sendRedirect(UrlConstants.EMPLOYEE_LIST_URL);
        } catch (ValidationExceptions e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            request.setAttribute(NormalConstants.EMPLOYEE, emp);
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
            employeeService.create(employeeFactory.createObjects(setDataAttribute(request)));
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
