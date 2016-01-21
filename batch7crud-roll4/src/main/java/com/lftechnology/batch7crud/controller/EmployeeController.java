package com.lftechnology.batch7crud.controller;

import static com.lftechnology.batch7crud.constant.AttribConstants.*;
import static com.lftechnology.batch7crud.constant.RouteConstants.*;
import static com.lftechnology.batch7crud.constant.UrlConstants.*;
import static com.lftechnology.batch7crud.constant.ParamConstants.*;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.service.EmployeeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by pratishshr on 1/14/16.
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

    if (parameters.length == 2) {
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
      int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / 20);
      int recordLimit = 20;

      if (page != 1 && page > noOfPages) {
        show404(request, response);
        return;
      }

      request.setAttribute(ATTRIB_EMPLOYEES, employeeService.fetch(page, recordLimit));
      request.setAttribute(ATTRIB_CURRENT_PAGE, page);
      request.setAttribute(ATTRIB_NO_OF_PAGES, noOfPages);

      RequestDispatcher view = request.getRequestDispatcher(URL_EMPLOYEE_LISTING_PAGE);
      view.forward(request, response);

    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      show500(request, response, e);
    } catch (NumberFormatException e) {
      show404(request, response);
    }
  }

  private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher view = request.getRequestDispatcher(URL_EMPLOYEE_CREATE_PAGE);
    view.forward(request, response);
  }

  private void createProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String firstName = request.getParameter(PARAM_FIRST_NAME);
    String lastName = request.getParameter(PARAM_LAST_NAME);
    String station = request.getParameter(PARAM_STATION);

    Employee employee = new Employee();
    employee.setFirstName(firstName);
    employee.setLastName(lastName);
    employee.setStation(station);

    try {
      employeeService.save(employee);
      response.sendRedirect(request.getContextPath() + ROUTE_EMPLOYEES);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      show500(request, response, e);
    }
  }

  private void viewProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      int employeeId = parameterValueAsInt(request, 2);

      request.setAttribute(ATTRIB_EMPLOYEE, employeeService.fetchById(employeeId));
      RequestDispatcher view = request.getRequestDispatcher(URL_EMPLOYEE_PROFILE_PAGE);
      view.forward(request, response);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      show500(request, response, e);
    } catch (NumberFormatException e) {
      show404(request, response);
    }
  }

  private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      int employeeId = parameterValueAsInt(request, 2);

      request.setAttribute(ATTRIB_EMPLOYEE, employeeService.fetchById(employeeId));
      RequestDispatcher view = request.getRequestDispatcher(URL_EMPLOYEE_EDIT_PAGE);
      view.forward(request, response);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      show500(request, response, e);
    } catch (NumberFormatException e) {
      show404(request, response);
    }

  }

  private void editProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      int employeeId = parameterValueAsInt(request, 2);

      String firstName = request.getParameter(PARAM_FIRST_NAME);
      String lastName = request.getParameter(PARAM_LAST_NAME);
      String station = request.getParameter(PARAM_STATION);

      Employee employee = new Employee();
      employee.setId(employeeId);
      employee.setFirstName(firstName);
      employee.setLastName(lastName);
      employee.setStation(station);

      employeeService.update(employee);
      response.sendRedirect(request.getContextPath() + ROUTE_EMPLOYEES);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      show500(request, response, e);
    } catch (NumberFormatException e) {
      show404(request, response);
    }

  }

  private void deleteProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      int employeeId = parameterValueAsInt(request, 2);
      employeeService.deleteEmployee(employeeId);
      response.sendRedirect(request.getContextPath() + ROUTE_EMPLOYEES);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      show500(request, response, e);
    } catch (NumberFormatException e) {
      show404(request, response);
    }
  }
}