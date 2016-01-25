package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constants.AppConstants;
import com.lftechnology.batch7crud.constants.AttributeConstants;
import com.lftechnology.batch7crud.constants.UrlConstants;
import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.services.EmployeeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Grishma Shrestha grishmashrestha@lftechnology.com on 1/19/16.
 */
@WebServlet({ "/employees/*" })
public class EmployeesController extends CommonHttpServlet {
  private static final Logger LOGGER = Logger.getLogger(EmployeesController.class.getName());
  private EmployeeService employeeService = new EmployeeService(); // NOSONAR

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String path = request.getPathInfo();
    if (path == null) {
      fetch(request, response);
    }
    else {
      String[] parts = path.split(UrlConstants.PATH_SEPARATOR);
      if (parts.length == 0) {
        fetch(request, response);
      }
      else if (parts.length == 2 && AppConstants.CREATE.equals(parts[1])) {
        create(request, response);
      }
      else if (parts.length == 2) {
        view(request, response);
      }
      else if (parts.length == 3 && AppConstants.EDIT.equals(parts[2])) {
        edit(request, response);
      }
      else {
        pageNotFound(request, response);
      }
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String path = request.getPathInfo();
    if (path == null) {
      request.getServletContext().getRequestDispatcher(request.getContextPath() + UrlConstants.EMPLOYEE_LIST_PAGE).forward(request,
        response);
    }
    else {
      String[] parts = path.split(UrlConstants.PATH_SEPARATOR);
      if (AppConstants.CREATE.equals(parts[1])) {
        createProcess(request, response);
      }
      else if (AppConstants.EDIT.equals(parts[2])) {
        editProcess(request, response);
      }
      else if (AppConstants.DELETE.equals(parts[2])) {
        deleteProcess(request, response);
      }
    }
  }

  private void create(HttpServletRequest request, HttpServletResponse response) {
    try {
      request.getServletContext().getRequestDispatcher(request.getContextPath() + UrlConstants.EMPLOYEE_CREATE_PAGE).forward(request, response);
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      errorPage(request, response, e.getMessage());
    }
  }

  private void edit(HttpServletRequest request, HttpServletResponse response) {
    try {
      int id = parameterValueAsInt(request, 2);
      Employee employee = employeeService.fetchById(id);
      request.setAttribute(AttributeConstants.EMPLOYEE, employee);
      RequestDispatcher dispatcher = request.getRequestDispatcher(request.getContextPath() + UrlConstants.EMPLOYEE_EDIT_PAGE);
      dispatcher.forward(request, response);
    }
    catch (Exception e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      errorPage(request, response, e.getMessage());
    }
  }

  private Employee setEmployeeAttributes (HttpServletRequest request) {
    String name = request.getParameter(AttributeConstants.NAME);
    String address = request.getParameter(AttributeConstants.ADDRESS);
    String designation = request.getParameter(AttributeConstants.DESIGNATION);
    String phone = request.getParameter(AttributeConstants.PHONE);

    Employee employee = new Employee();
    employee.setName(name);
    employee.setAddress(address);
    employee.setDesignation(designation);
    employee.setPhone(phone);
    return employee;
  }

  private void createProcess(HttpServletRequest request, HttpServletResponse response) {
    try {
      Employee employee = setEmployeeAttributes(request);
      employeeService.create(employee);
      response.sendRedirect(request.getContextPath() + UrlConstants.EMPLOYEE_ROUTE);
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      errorPage(request, response, e.getMessage());
    }
  }

  private void editProcess(HttpServletRequest request, HttpServletResponse response) {
    try{
      int id = parameterValueAsInt(request, 2);
      Employee employee = setEmployeeAttributes(request);
      employee.setId(id);
      employeeService.edit(employee);
      response.sendRedirect(request.getContextPath() + UrlConstants.EMPLOYEE_ROUTE);
    }
    catch (Exception e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      errorPage(request, response, e.getMessage());
    }
  }

  private void deleteProcess(HttpServletRequest request, HttpServletResponse response) {
    try {
      int id = parameterValueAsInt(request, 2);
      employeeService.delete(id);
      response.sendRedirect(request.getContextPath() + UrlConstants.EMPLOYEE_ROUTE);
    }
    catch (Exception e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      errorPage(request, response, e.getMessage());
    }
  }

  private void fetch(HttpServletRequest request, HttpServletResponse response) {
    try {
      Integer pageLimit = AppConstants.PAGE_LIMIT;
      Integer pageNo = getCurrentPage(request);
      Integer offset = (pageNo-1)*pageLimit;

      List<Employee> employeeList = employeeService.fetch(pageLimit, offset);
      Integer employeeCount = employeeService.count();
      request.setAttribute(AttributeConstants.EMPLOYEE_LIST, employeeList);
      request.setAttribute(AttributeConstants.EMPLOYEE_COUNT, employeeCount);
      request.setAttribute(AttributeConstants.PAGE_NO, pageNo);
      if ((employeeCount%pageLimit) == 0) {
        request.setAttribute(AttributeConstants.LAST_PAGE_NO, employeeCount / pageLimit);
      }
      else {
        request.setAttribute(AttributeConstants.LAST_PAGE_NO, (employeeCount / pageLimit) + 1);
      }
      request.getServletContext().getRequestDispatcher(request.getContextPath() + UrlConstants.EMPLOYEE_LIST_PAGE).forward(request, response);
    }
    catch (Exception e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      errorPage(request, response, e.getMessage());
    }
  }

  private void view(HttpServletRequest request, HttpServletResponse response) {
    try {
      int id = parameterValueAsInt(request, 2);
      Employee employee = employeeService.fetchById(id);
      if (employee != null) {
        request.setAttribute(AttributeConstants.EMPLOYEE, employee);
        request.getRequestDispatcher(request.getContextPath() + UrlConstants.EMPLOYEE_VIEW_PAGE).forward(request, response);
      }
      else {
        pageNotFound(request, response);
      }
    }
    catch (NumberFormatException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      pageNotFound(request, response);
    }
    catch (Exception e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      errorPage(request, response, e.getMessage());
    }
  }

  private int getCurrentPage(HttpServletRequest request) {
    try {
      if (request.getParameter(AttributeConstants.PAGE) == null) {
        return 1;
      } else {
        return Integer.parseInt(request.getParameter(AttributeConstants.PAGE));
      }
    } catch(NumberFormatException e){
      return 1;
    }
  }
}