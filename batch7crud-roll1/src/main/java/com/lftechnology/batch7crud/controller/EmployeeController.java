package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.entity.Employee;
import com.lftechnology.batch7crud.service.EmployeeService;

import static com.lftechnology.batch7crud.constant.EntityConstant.*;
import static com.lftechnology.batch7crud.constant.PageConstant.*;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
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
  private static EmployeeService employeeService = new EmployeeService();
  private static final Logger LOGGER = Logger.getLogger(EmployeeController.class.getName());
  public static final long serialVersionUID = 1L;
  private static final int RECORD_TO_FETCH = 2;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public EmployeeController() {
    super();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      String[] pathParts = params(request);
      if (pathParts.length <= 1) {
        int currentPage = 1;
        String pageText = request.getParameter(PAGE);
        if (pageText != null) {
          currentPage = Integer.parseInt(pageText);
        }
        list(request, response, currentPage);
      } else if (CREATE.equals(pathParts[1])) {
        create(request, response);
      } else if (EDIT.equals(pathParts[2])) {
        int id = Integer.parseInt(pathParts[1]);
        edit(request, response, id);
      } else {
        showPageNotFound(request, response);
      }
    } catch (ServletException | IOException | NumberFormatException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    try {
      String[] pathParts = params(request);
      if (CREATE.equals(pathParts[1])) {
        createProcess(request, response);
      } else if (EDIT.equals(pathParts[2])) {
        int id = Integer.parseInt(pathParts[1]);
        editProcess(request, response, id);
      } else if (DELETE.equals(pathParts[2])) {
        int id = Integer.parseInt(pathParts[1]);
        deleteProcess(request, response, id);
      }
    } catch (ServletException | IOException | NumberFormatException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  private void list(HttpServletRequest request, HttpServletResponse response, int currentPage) throws ServletException, IOException {
    try {

      int offset = (currentPage - 1) * RECORD_TO_FETCH;
      int totalRecord = employeeService.fetchNoOfRecords();
      int totalPage = (int) Math.ceil(totalRecord * 1.0 / RECORD_TO_FETCH);
      List<Employee> employees = employeeService.fetch(offset, RECORD_TO_FETCH);
      request.setAttribute("employees", employees);
      request.setAttribute("currentPage", currentPage);
      request.setAttribute("totalPage", totalPage);
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

  private void edit(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
    try {
      Employee employee = employeeService.fetchById(id);
      request.setAttribute("employee", employee);
      request.getServletContext().getRequestDispatcher(EDIT_PAGE).forward(request, response);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      showServerError(request, response, e);
    }
  }

  private void editProcess(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {

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

  private void deleteProcess(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
    try {
      employeeService.delete(id);
      response.sendRedirect(request.getContextPath() + EMPLOYEE_LIST);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      showServerError(request, response, e);
    }
  }
}
