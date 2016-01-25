package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.service.EmployeeService;
import com.lftechnology.batch7crud.validator.EmployeeValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.lftechnology.batch7crud.constant.AttribConstants.*;
import static com.lftechnology.batch7crud.constant.ParamConstants.*;
import static com.lftechnology.batch7crud.constant.RouteConstants.*;
import static com.lftechnology.batch7crud.constant.UrlConstants.*;

/**
 * Created by Pratish Shrestha <pratishshrestha@lftechnology.com> on 1/14/16.
 */

@WebServlet(name = "EmployeeController", urlPatterns = { "/employees/*" })
public class EmployeeController extends CustomHttpServlet {
  private static final Logger LOGGER = Logger.getLogger(EmployeeController.class.getName());

  private static EmployeeService employeeService;

  public EmployeeController() {
    employeeService = new EmployeeService();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String[] parameters = parameterValues(request);

    if (parameters.length == 2) { // for /employees , listing page
      list(request, response);
    } else if (parameters.length == 3 && ROUTE_CREATE.equals(parameters[2])) {
      create(request, response);
    } else if (parameters.length == 3) {
      viewProfile(request, response);
    } else if (parameters.length == 4 && ROUTE_EDIT.equals(parameters[3])) {
      edit(request, response);
    } else {
      show404(request, response);
    }

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String[] parameters = parameterValues(request);

    if (parameters.length == 3 && ROUTE_CREATE.equals(parameters[2])) {
      createProcess(request, response);
    } else if (parameters.length == 4 && ROUTE_EDIT.equals(parameters[3])) {
      editProcess(request, response);
    } else if (parameters.length == 4 && ROUTE_DELETE.equals(parameters[3])) {
      deleteProcess(request, response);
    } else {
      show404(request, response);
    }
  }

  private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      int page = pageNumber(request);
      int noOfRecords = employeeService.fetchNoOfRecords();
      int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / ATTRIB_RECORD_LIMIT);

      if (page != 1 && page > noOfPages) {
        show404(request, response);
        return;
      }

      request.setAttribute(ATTRIB_EMPLOYEES, employeeService.fetch(page, ATTRIB_RECORD_LIMIT));
      request.setAttribute(ATTRIB_CURRENT_PAGE, page);
      request.setAttribute(ATTRIB_NO_OF_PAGES, noOfPages);

      RequestDispatcher view = request.getRequestDispatcher(URL_EMPLOYEE_LISTING_PAGE);
      view.forward(request, response);

    } catch (DataException | NumberFormatException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      show500(request, response, e);
    }

  }

  private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher view = request.getRequestDispatcher(URL_EMPLOYEE_CREATE_PAGE);
    view.forward(request, response);
  }

  private void createProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Map<String, String> inputs = new HashMap<String, String>();

    inputs.put(PARAM_FIRST_NAME, request.getParameter(PARAM_FIRST_NAME));
    inputs.put(PARAM_LAST_NAME, request.getParameter(PARAM_LAST_NAME));
    inputs.put(PARAM_STATION, request.getParameter(PARAM_STATION));

    EmployeeValidator validator = new EmployeeValidator();
    Employee employee = validator.createObject(inputs);

    try {
      employeeService.save(employee);
      response.sendRedirect(request.getContextPath() + ROUTE_EMPLOYEES);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      show500(request, response, e);
    } catch (ValidationException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      request.setAttribute(ATTRIB_ERRORS, e.getErrors());
      request.setAttribute(ATTRIB_EMPLOYEE, employee);

      RequestDispatcher view = request.getRequestDispatcher(URL_EMPLOYEE_CREATE_PAGE);
      view.forward(request, response);
    }

  }

  private void viewProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      int employeeId = parameterValueAsInt(request, 2);

      request.setAttribute(ATTRIB_EMPLOYEE, employeeService.fetchById(employeeId));
      RequestDispatcher view = request.getRequestDispatcher(URL_EMPLOYEE_PROFILE_PAGE);
      view.forward(request, response);
    } catch (DataException | NumberFormatException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      show500(request, response, e);
    }

  }

  private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      int employeeId = parameterValueAsInt(request, 2);

      request.setAttribute(ATTRIB_EMPLOYEE, employeeService.fetchById(employeeId));
      RequestDispatcher view = request.getRequestDispatcher(URL_EMPLOYEE_EDIT_PAGE);
      view.forward(request, response);
    } catch (DataException | NumberFormatException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      show500(request, response, e);
    }

  }

  private void editProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Map<String, String> inputs = new HashMap<String, String>();

    int employeeId = parameterValueAsInt(request, 2);

    inputs.put(PARAM_FIRST_NAME, request.getParameter(PARAM_FIRST_NAME));
    inputs.put(PARAM_LAST_NAME, request.getParameter(PARAM_LAST_NAME));
    inputs.put(PARAM_STATION, request.getParameter(PARAM_STATION));

    EmployeeValidator validator = new EmployeeValidator();
    Employee employee = validator.createObject(inputs);
    employee.setId(employeeId);

    try {
      employeeService.update(employee);
      response.sendRedirect(request.getContextPath() + ROUTE_EMPLOYEES);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      show500(request, response, e);
    } catch (ValidationException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      request.setAttribute(ATTRIB_ERRORS, e.getErrors());
      request.setAttribute(ATTRIB_EMPLOYEE, employee);

      RequestDispatcher view = request.getRequestDispatcher(URL_EMPLOYEE_EDIT_PAGE);
      view.forward(request, response);
    }
  }

  private void deleteProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      int employeeId = parameterValueAsInt(request, 2);
      employeeService.deleteEmployee(employeeId);
      response.sendRedirect(request.getContextPath() + ROUTE_EMPLOYEES);
    } catch (DataException | NumberFormatException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      show500(request, response, e);
    }
  }
}