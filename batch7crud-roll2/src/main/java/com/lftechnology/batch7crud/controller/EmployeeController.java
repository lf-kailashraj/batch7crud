package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constants.AppConstants;
import com.lftechnology.batch7crud.constants.AttributeConstants;
import com.lftechnology.batch7crud.constants.UrlConstants;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.factory.EmployeeFactory;
import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Romit Amgai <romitamgai@lftechnology.com> on 1/19/16.
 */
@WebServlet(name = "EmployeeController", urlPatterns = { "/employees/*" })
public class EmployeeController extends CommonHttpServlet {
  private static final Logger LOGGER = Logger.getLogger(EmployeeController.class.getName());
  private EmployeeService employeeService;  //NOSONAR

  public EmployeeController() {
    employeeService = new EmployeeService();
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String page = getPageFromPath(request);
    switch (page) {
    case AppConstants.CREATE:
      createProcess(request, response);
      break;
    case AppConstants.EDIT:
      editProcess(request, response);
      break;
    case AppConstants.DELETE:
      deleteProcess(request, response);
      break;
    default:
      throw new ServletException(AppConstants.PAGE_NOT_FOUND_MESSAGE);
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String page = getPageFromPath(request);
    switch (page) {
    case AppConstants.EMPLOYEE:
      list(request, response);
      break;
    case AppConstants.EDIT:
      edit(request, response);
      break;
    case AppConstants.CREATE:
      create(request, response);
      break;
    case AppConstants.VIEW:
      view(request, response);
      break;
    default:
      throw new ServletException(AppConstants.PAGE_NOT_FOUND_MESSAGE);
    }
  }

  private Map<String, String> mapParameters(HttpServletRequest request) {
    Map<String, String> inputs = new HashMap<>();
    inputs.put(AttributeConstants.NAME, request.getParameter(AttributeConstants.NAME));
    inputs.put(AttributeConstants.ADDRESS, request.getParameter(AttributeConstants.ADDRESS));
    inputs.put(AttributeConstants.EMAIL, request.getParameter(AttributeConstants.EMAIL));
    inputs.put(AttributeConstants.CONTACT, request.getParameter(AttributeConstants.CONTACT));
    return inputs;
  }

  private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      int page = getPageNo(request);
      int recordsPerPage = 10;
      List<Employee> empList = employeeService.fetch(10, (page - 1) * recordsPerPage);
      int totalNoOfRecords = employeeService.getTotalNoOfRecords();
      int validNoOfPages = (int) Math.ceil(totalNoOfRecords * 1.0 / 10);
      if (page != 1 && page > validNoOfPages) {
        throw new ServletException(AppConstants.PAGE_NOT_FOUND_MESSAGE);
      }
      request.setAttribute(AttributeConstants.EMPLOYEE_LIST, empList);
      request.setAttribute(AttributeConstants.NO_OF_PAGES, validNoOfPages);
      request.setAttribute(AttributeConstants.CURRENT_PAGE, page);
      request.getRequestDispatcher(UrlConstants.EMPLOYEE_LIST_PAGE).forward(request, response);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new ServletException(AppConstants.INTERNAL_SERVER_ERROR_MESSAGE);
    }
  }

  private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher(UrlConstants.EMPLOYEE_CREATE_PAGE).forward(request, response);
  }

  private void createProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Employee employee = null;
    try {
      Map<String, String> inputs = mapParameters(request);
      EmployeeFactory employeeFactory = new EmployeeFactory();
      employee = employeeFactory.createObject(inputs);

      employeeService.insert(employee);
      response.sendRedirect(request.getContextPath() + UrlConstants.EMPLOYEE_ROUTE);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new ServletException(AppConstants.INTERNAL_SERVER_ERROR_MESSAGE);
    } catch (ValidationException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      request.setAttribute(AttributeConstants.EMPLOYEE, employee);
      request.setAttribute(AttributeConstants.VALIDATION_MESSAGE, e.getErrors());
      request.getRequestDispatcher(UrlConstants.EMPLOYEE_CREATE_PAGE).forward(request, response);
    }
  }

  private void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      int id = parameterValueAsInt(request, 2);
      Employee employee = employeeService.fetchById(id);
      if (employee == null) {
        throw new ServletException(AppConstants.PAGE_NOT_FOUND_MESSAGE);
      }
      request.setAttribute(AttributeConstants.EMPLOYEE, employee);
      request.getRequestDispatcher(UrlConstants.EMPLOYEE_VIEW_PAGE).forward(request, response);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new ServletException(AppConstants.INTERNAL_SERVER_ERROR_MESSAGE);
    } catch (NumberFormatException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new ServletException(AppConstants.PAGE_NOT_FOUND_MESSAGE);
    }
  }

  private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      int id = parameterValueAsInt(request, 2);
      Employee employee = employeeService.fetchById(id);
      if (employee == null) {
        throw new ServletException(AppConstants.PAGE_NOT_FOUND_MESSAGE);
      }
      request.setAttribute(AttributeConstants.EMPLOYEE, employee);
      request.getRequestDispatcher(UrlConstants.EMPLOYEE_EDIT_PAGE).forward(request, response);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new ServletException(AppConstants.INTERNAL_SERVER_ERROR_MESSAGE);
    } catch (NumberFormatException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new ServletException(AppConstants.PAGE_NOT_FOUND_MESSAGE);
    }
  }

  private void editProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int id = parameterValueAsInt(request, 2);
    Employee employee = null;
    try {
      Map<String, String> inputs = mapParameters(request);
      EmployeeFactory employeeFactory = new EmployeeFactory();
      employee = employeeFactory.createObject(inputs);
      employee.setId(id);
      employeeService.update(employee);
      response.sendRedirect(request.getContextPath() + UrlConstants.EMPLOYEE_ROUTE);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new ServletException(AppConstants.INTERNAL_SERVER_ERROR_MESSAGE);
    } catch (NumberFormatException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new ServletException(AppConstants.PAGE_NOT_FOUND_MESSAGE);
    } catch (ValidationException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      request.setAttribute(AttributeConstants.EMPLOYEE, employee);
      request.setAttribute(AttributeConstants.VALIDATION_MESSAGE, e.getErrors());
      request.getRequestDispatcher(UrlConstants.EMPLOYEE_EDIT_PAGE).forward(request, response);
    }
  }

  private void deleteProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      int id = parameterValueAsInt(request, 2);
      employeeService.delete(id);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new ServletException(AppConstants.INTERNAL_SERVER_ERROR_MESSAGE);
    }
    response.sendRedirect(request.getContextPath() + UrlConstants.EMPLOYEE_ROUTE);
  }
}