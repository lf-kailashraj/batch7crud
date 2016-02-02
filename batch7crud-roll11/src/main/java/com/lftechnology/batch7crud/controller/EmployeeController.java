package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constant.CommonConstants;
import com.lftechnology.batch7crud.constant.EmployeeConstants;
import com.lftechnology.batch7crud.constant.PathConstants;
import com.lftechnology.batch7crud.constant.UrlConstants;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.service.EmployeeService;
import com.lftechnology.batch7crud.util.StringUtil;
import com.lftechnology.batch7crud.validator.EmployeeValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
    String[] parsedUrl = StringUtil.parseUrl(request.getRequestURI());
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
    String[] parsedUrl = StringUtil.parseUrl(request.getRequestURI().substring(request.getContextPath().length()));
    try {
      if (parsedUrl.length == 3 && CommonConstants.ADD.equals(parsedUrl[2])) {
        this.create(request, response);
      } else if (parsedUrl.length == 4 && CommonConstants.EDIT.equals(parsedUrl[3])) {
        this.update(request, response, parsedUrl[2]);
      } else if (parsedUrl.length == 4 && CommonConstants.DELETE.equals(parsedUrl[3])) {
        employeeService.delete(Integer.parseInt(parsedUrl[2]));
        response.sendRedirect(PathConstants.EMPLOYEE);
      }
    } catch (ServletException | HTTPException | DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
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
    Map<String, String> inputs = this.putInputToMap(request);
    EmployeeValidator validator = new EmployeeValidator();
    try {
      validator.validateEmpty(inputs);
      Employee employee = new Employee(inputs);
      employeeService.create(employee);
      response.sendRedirect(PathConstants.EMPLOYEE);
    }catch (ValidationException e){
      LOGGER.log(Level.SEVERE, "Validation error occurred", e);
      request.setAttribute(CommonConstants.ERRORS, e.getErrors());
      request.getRequestDispatcher(UrlConstants.ADD_EMPLOYEE).forward(request, response);
    }
  }

  public void edit(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException, DataException { //NOSONAR
    Employee employee = new Employee();
    employee = employeeService.getEmployeeById(id);
    if(employee == null){
      response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
    request.setAttribute(CommonConstants.EMPLOYEE, employee);
    request.getRequestDispatcher(UrlConstants.EDIT_EMPLOYEE).forward(request, response);
  }

  public void update(HttpServletRequest request, HttpServletResponse response, String id) throws ServletException, IOException, DataException { //NOSONAR
    Map<String, String> inputs = this.putInputToMap(request);
    EmployeeValidator validator = new EmployeeValidator();
    try {
      validator.validateEmpty(inputs);
      Employee employee = new Employee(inputs);
      employee.setEmpId(Integer.parseInt(id));
      employeeService.update(employee);
      response.sendRedirect(PathConstants.EMPLOYEE);
    }catch (ValidationException e){
      LOGGER.log(Level.SEVERE, "Validation error occurred",e);
      request.setAttribute(CommonConstants.ERRORS, e.getErrors());
      inputs.put(CommonConstants.EMP_ID, id);
      request.setAttribute(CommonConstants.EMPLOYEE, inputs);
      request.getRequestDispatcher(UrlConstants.EDIT_EMPLOYEE).forward(request, response);
    }
  }

  public Map<String, String> putInputToMap(HttpServletRequest request){
    Map<String, String> temp = new HashMap<>();
    temp.put(EmployeeConstants.NAME, request.getParameter(EmployeeConstants.NAME));
    temp.put(EmployeeConstants.ADDRESS, request.getParameter(EmployeeConstants.ADDRESS));
    temp.put(EmployeeConstants.DEPARTMENT, request.getParameter(EmployeeConstants.DEPARTMENT));
    temp.put(EmployeeConstants.POSITION, request.getParameter(EmployeeConstants.POSITION));
    temp.put(EmployeeConstants.SALARY, request.getParameter(EmployeeConstants.SALARY));
    return temp;
  }
}
