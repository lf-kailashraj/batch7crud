package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Romit Amgai <romitamgai@lftechnology.com> on 1/19/16.
 */
@WebServlet(name = "EmployeeController", urlPatterns = { "/employees/*" })
public class EmployeeController extends CommonHttpServlet {
  private static final Logger LOGGER = Logger.getLogger("employeeLogger");
  private static final String EMPLOYEE_PATH = "/employees";

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String[] pathParts = getPathParameters(request);

    if (pathParts.length == 3 && "create".equals(pathParts[2])) {
      createProcess(request, response);
    } else if (pathParts.length == 4 && "edit".equals(pathParts[3])) {
      editProcess(request, response);
    } else if (pathParts.length == 4 && "delete".equals(pathParts[3])) {
      deleteProcess(request, response);
    } else {
      displayPageNotFound(request, response);
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String[] pathParts = getPathParameters(request);

    if (pathParts.length == 2 && "employees".equals(pathParts[1])) {
      list(request, response);
    } else if (pathParts.length == 3 && "create".equals(pathParts[2])) {
      create(request, response);
    } else if (pathParts.length == 4 && "edit".equals(pathParts[3])) {
      edit(request, response);
    } else if (pathParts.length == 3) {
      view(request, response);
    } else {
      displayPageNotFound(request, response);
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
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      displayErrorPage(request, response, e.getMessage());
    }
  }

  private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("/WEB-INF/views/employeeCreate.jsp").forward(request, response);
  }

  private void createProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      String name = request.getParameter("name");
      String address = request.getParameter("address");
      String email = request.getParameter("email");
      String contact = request.getParameter("contact");

      Employee employee = new Employee();
      employee.setName(name);
      employee.setAddress(address);
      employee.setEmail(email);
      employee.setContact(contact);

      EmployeeService employeeService = new EmployeeService();
      employeeService.insert(employee);
      response.sendRedirect(request.getContextPath() + EMPLOYEE_PATH);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      displayErrorPage(request, response, e.getMessage());
    }
  }

  private void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      int id = parameterValueAsInt(request, 2);
      EmployeeService employeeService = new EmployeeService();
      Employee employee = employeeService.fetchById(id);
      if (employee == null) {
        displayPageNotFound(request, response);
      }
      request.setAttribute("employee", employee);
      request.getRequestDispatcher("/WEB-INF/views/employeeView.jsp").forward(request, response);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      displayErrorPage(request, response, e.getMessage());
    } catch (NumberFormatException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      displayPageNotFound(request, response);
    }
  }

  private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      int id = parameterValueAsInt(request, 2);
      EmployeeService employeeService = new EmployeeService();
      Employee employee = employeeService.fetchById(id);
      if (employee == null) {
        displayPageNotFound(request, response);
      }
      request.setAttribute("employee", employee);
      request.getRequestDispatcher("/WEB-INF/views/employeeEdit.jsp").forward(request, response);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      displayErrorPage(request, response, e.getMessage());
    }
  }

  private void editProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      int id = parameterValueAsInt(request, 2);
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

      EmployeeService employeeService = new EmployeeService();
      employeeService.update(employee);
      response.sendRedirect(request.getContextPath() + EMPLOYEE_PATH);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      displayErrorPage(request, response, e.getMessage());
    }
  }

  private void deleteProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      int id = parameterValueAsInt(request, 2);
      EmployeeService employeeService = new EmployeeService();
      employeeService.delete(id);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      displayErrorPage(request, response, e.getMessage());
    }
    response.sendRedirect(request.getContextPath() + EMPLOYEE_PATH);
  }
}