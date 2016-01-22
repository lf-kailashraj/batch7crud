package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.entity.Employee;
import com.lftechnology.batch7crud.service.EmployeeService;

import static com.lftechnology.batch7crud.constant.EntityConstant.*;
import static com.lftechnology.batch7crud.constant.URLConstant.*;
import static com.lftechnology.batch7crud.constant.AttributeConstant.*;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class handles request comming for employees, performs operations such as create, edit, delete
 * employees and send response to view
 * Servlet implementation class EmployeeController
 *
 * @Author Kiran Pariyar <kiranpariyar@lftechnology.com>
 * Created on 1/16/16
 */

@WebServlet({ "/employees/*" })
public class EmployeeController extends CustomHttpServlet {
  private EmployeeService employeeService = new EmployeeService();  //NOSONAR
  private static final Logger LOGGER = Logger.getLogger(EmployeeController.class.getName());
  private static final int RECORD_TO_FETCH = 2;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      String[] pathParts = getPathParameters(request);
      if (pathParts.length == 2 && EMPLOYEES.equals(pathParts[1])) {
        list(request, response);
      } else if (pathParts.length == 3 && CREATE.equals(pathParts[2])) {
        create(request, response);
      } else if (pathParts.length == 4 && EDIT.equals(pathParts[3])) {
        edit(request, response);
      } else {
        showPageNotFound(request, response);
      }
    } catch (ServletException | IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    try {
      String[] pathParts = getPathParameters(request);

      if (pathParts.length == 3 && CREATE.equals(pathParts[2])) {
        createProcess(request, response);
      } else if (pathParts.length == 4 && EDIT.equals(pathParts[3])) {
        editProcess(request, response);
      } else if (pathParts.length == 4 && DELETE.equals(pathParts[3])) {
        deleteProcess(request, response);
      } else {
        showPageNotFound(request, response);
      }
    } catch (ServletException | IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {

      int currentPage = 1;
      String pageText = request.getParameter(PAGE);
      if (pageText != null) {
        currentPage = Integer.parseInt(pageText);
      }

      int offset = (currentPage - 1) * RECORD_TO_FETCH;
      int totalRecord = employeeService.fetchNoOfRecords();
      int totalPage = (int) Math.ceil(totalRecord * 1.0 / RECORD_TO_FETCH);
      List<Employee> employees = employeeService.fetch(offset, RECORD_TO_FETCH);
      request.setAttribute(EMPLOYEES, employees);
      request.setAttribute(CURRENT_PAGE, currentPage);
      request.setAttribute(TOTAL_PAGE, totalPage);
      request.getServletContext().getRequestDispatcher(LIST_PAGE).forward(request, response);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      showServerError(request, response, e);
    }
  }

  private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getServletContext().getRequestDispatcher(CREATE_PAGE).forward(request, response);
  }

  private void createProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String userName = request.getParameter(USER_NAME);
    String password = request.getParameter(PASSWORD);
    String fullName = request.getParameter(FULL_NAME);
    String department = request.getParameter(DEPARTMENT);
    String address = request.getParameter(ADDRESS);
    Employee employee = new Employee(userName, password, fullName, department, address);

    try {
      employeeService.create(employee);
      response.sendRedirect(request.getContextPath() + EMPLOYEE_LIST);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      showServerError(request, response, e);
    }
  }

  private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      int id = parameterValueAsInt(request, 2);
      Employee employee = employeeService.fetchById(id);
      if (employee == null) {
        showPageNotFound(request, response);
      }
      request.setAttribute(EMPLOYEE, employee);
      request.getServletContext().getRequestDispatcher(EDIT_PAGE).forward(request, response);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      showServerError(request, response, e);
    }
  }

  private void editProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    int id = parameterValueAsInt(request, 2);
    String userName = request.getParameter(USER_NAME);
    String password = request.getParameter(PASSWORD);
    String fullName = request.getParameter(FULL_NAME);
    String department = request.getParameter(DEPARTMENT);
    String address = request.getParameter(ADDRESS);
    Employee employee = new Employee(userName, password, fullName, department, address);
    employee.setId(id);

    try {
      employeeService.update(employee);
      response.sendRedirect(request.getContextPath() + EMPLOYEE_LIST);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      showServerError(request, response, e);
    }
  }

  private void deleteProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      int id = parameterValueAsInt(request, 2);
      employeeService.delete(id);
      response.sendRedirect(request.getContextPath() + EMPLOYEE_LIST);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      showServerError(request, response, e);
    }
  }
}
