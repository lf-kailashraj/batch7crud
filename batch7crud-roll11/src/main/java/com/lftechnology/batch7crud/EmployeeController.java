package com.lftechnology.batch7crud;

import com.lftechnology.batch7crud.constant.CommonConstants;
import com.lftechnology.batch7crud.constant.EmployeeConstants;
import com.lftechnology.batch7crud.constant.UrlConstants;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by achyut on 1/26/16.
 */
@WebServlet(name = "EmployeeController", urlPatterns = {"/employee/*"})
public class EmployeeController extends HttpServlet {
  private static final Logger LOGGER = Logger.getLogger(EmployeeController.class.getName());
  private EmployeeService employeeService = new EmployeeService(); //NOSONAR

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    String[] parsedUrl = this.parseUrl(request);
    try {
      if (parsedUrl.length == 2) {
        this.getAllStudents(request, response);
      } else if (parsedUrl.length == 3 && CommonConstants.ADD.equals(parsedUrl[2])) {
        request.getRequestDispatcher(UrlConstants.ADD_EMPLOYEE).forward(request, response);
      } else if (parsedUrl.length == 4 && CommonConstants.EDIT.equals(parsedUrl[3])) {
        this.edit(request, response, Integer.parseInt(parsedUrl[2]));
      } else {
        LOGGER.log(Level.SEVERE, request.getRequestURL() + " not found");
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
      }
    } catch (ServletException | HTTPException | DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    String[] parsedUrl = this.parseUrl(request);
    try {
      if (parsedUrl.length == 3 && CommonConstants.ADD.equals(parsedUrl[2])) {
        this.create(request, response);
      } else if (parsedUrl.length == 4 && CommonConstants.EDIT.equals(parsedUrl[3])) {
        this.update(request, response, Integer.parseInt(parsedUrl[2]));
      } else if (parsedUrl.length == 4 && CommonConstants.DELETE.equals(parsedUrl[3])) {
        employeeService.delete(Integer.parseInt(parsedUrl[2]));
        response.sendRedirect("/" + CommonConstants.EMPLOYEE);
      }
    } catch (ServletException | HTTPException | DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }

  }

  public String[] parseUrl(HttpServletRequest request) {
    String urlPath = request.getRequestURI().substring(request.getContextPath().length());
    return urlPath.split("/");
  }

  public void getAllStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DataException { //NOSONAR
    try {
      request.setAttribute(CommonConstants.EMPLOYEES, employeeService.getAllStudents());
      request.getRequestDispatcher(UrlConstants.LIST_EMPLOYEE).forward(request, response);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

  public void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DataException { //NOSONAR
    Employee employee = new Employee();
    employee.setName(request.getParameter(EmployeeConstants.NAME));
    employee.setAddress(request.getParameter(EmployeeConstants.ADDRESS));
    employee.setDepartment(request.getParameter(EmployeeConstants.DEPARTMENT));
    employee.setPosition(request.getParameter(EmployeeConstants.POSITION));
    employee.setSalary(Integer.parseInt(request.getParameter(EmployeeConstants.SALARY)));

    employeeService.create(employee);
    response.sendRedirect("/" + CommonConstants.EMPLOYEE);
  }

  public void edit(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException, DataException { //NOSONAR
    request.setAttribute(CommonConstants.EMPLOYEE, employeeService.getEmployeeById(id));
    request.getRequestDispatcher(UrlConstants.EDIT_EMPLOYEE).forward(request, response);
  }

  public void update(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException, DataException { //NOSONAR
    Employee employee = new Employee();
    employee.setEmpId(id);
    employee.setName(request.getParameter(EmployeeConstants.NAME));
    employee.setAddress(request.getParameter(EmployeeConstants.ADDRESS));
    employee.setDepartment(request.getParameter(EmployeeConstants.DEPARTMENT));
    employee.setPosition(request.getParameter(EmployeeConstants.POSITION));
    employee.setSalary(Integer.parseInt(request.getParameter(EmployeeConstants.SALARY)));

    employeeService.update(employee);
    response.sendRedirect("/" + CommonConstants.EMPLOYEE);
  }
}
