package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constants.AppConstants;
import com.lftechnology.batch7crud.constants.AttributeConstants;
import com.lftechnology.batch7crud.constants.UrlConstants;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.factory.EmployeeFactory;
import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.services.EmployeeService;
import com.lftechnology.batch7crud.validator.EmployeeValidator;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Grishma Shrestha grishmashrestha@lftechnology.com on 1/19/16.
 */
@WebServlet({ "/employees/*" })
public class EmployeeController extends CommonHttpServlet {
  private static final Logger LOGGER = Logger.getLogger(EmployeeController.class.getName());
  private EmployeeService employeeService = new EmployeeService(); // NOSONAR
  private EmployeeValidator validator = new EmployeeValidator(); // NOSONAR

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    try {
      String path = request.getPathInfo();
      if (path == null) {
        fetch(request, response);
      }
      else {
        String action = getAction(request);
        switch (action) {
        case AppConstants.FETCH:
          fetch(request, response);
          break;
        case AppConstants.CREATE:
          create(request, response);
          break;
        case AppConstants.CREATE2:
          create2(request, response);
          break;
        case AppConstants.EDIT:
          edit(request, response);
          break;
        case AppConstants.VIEW:
          view(request, response);
          break;
        default:
          throw new ServletException(AppConstants.PAGE_NOT_FOUND_MESSAGE);
        }
      }
    }
    catch (Exception e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new ServletException(e.getMessage());
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    try {
      String action = postAction(request);

      switch (action) {
      case AppConstants.CREATE:
        createProcess(request, response);
        break;
      case AppConstants.CREATE2:
        createProcess2(request, response);
        break;
      case AppConstants.EDIT:
        editProcess(request, response);
        break;
      case AppConstants.DELETE:
        deleteProcess(request, response);
        break;
      default:
        request.setAttribute(AttributeConstants.MESSAGE, AppConstants.PAGE_NOT_FOUND_MESSAGE);
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
      }
    }
    catch (Exception e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new ServletException(e.getMessage());
    }
  }

  private void create(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    try {
      request.getServletContext().getRequestDispatcher(request.getContextPath() + UrlConstants.EMPLOYEE_CREATE_PAGE).forward(request,
        response);
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new ServletException(e.getMessage());
    }
  }

  private void create2(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    try {
      request.getServletContext().getRequestDispatcher(request.getContextPath() + UrlConstants.EMPLOYEE_CREATE_PAGE2).forward(request,
        response);
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new ServletException(e.getMessage());
    }
  }

  private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    try {
      int id = parameterValueAsInt(request, 2);
      Employee employee = employeeService.fetchById(id);
      if (employee != null) {
        request.setAttribute(AttributeConstants.EMPLOYEE, employee);
        RequestDispatcher dispatcher = request.getRequestDispatcher(request.getContextPath() + UrlConstants.EMPLOYEE_EDIT_PAGE);
        dispatcher.forward(request, response);
      }
      else {
        request.setAttribute(AttributeConstants.MESSAGE, AppConstants.PAGE_NOT_FOUND_MESSAGE);
        throw new ServletException(AppConstants.PAGE_NOT_FOUND_MESSAGE);
      }
    }
    catch (Exception e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      request.setAttribute(AttributeConstants.MESSAGE, e.getMessage());
      throw new ServletException(AppConstants.INTERNAL_SERVER_ERROR);
    }
  }

  private Map<String, String> setEmployeeAttributes (HttpServletRequest request) {
    Map<String, String> inputs = new HashMap<>();
    inputs.put(AttributeConstants.NAME, request.getParameter(AttributeConstants.NAME));
    inputs.put(AttributeConstants.ADDRESS, request.getParameter(AttributeConstants.ADDRESS));
    inputs.put(AttributeConstants.DESIGNATION, request.getParameter(AttributeConstants.DESIGNATION));
    inputs.put(AttributeConstants.PHONE, request.getParameter(AttributeConstants.PHONE));
    return inputs;
  }

  private Map<String, String> setEmployeeAttributesFromJSON (HttpServletRequest request) throws IOException {
    Map<String, String> inputs = new HashMap<>();

    BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
    String json = "";
    if(br != null){
      json = br.readLine();
    }
    JSONObject jsonObject = new JSONObject(json);

    inputs.put(AttributeConstants.NAME, (String) jsonObject.get(AttributeConstants.NAME));
    inputs.put(AttributeConstants.ADDRESS, (String) jsonObject.get(AttributeConstants.ADDRESS));
    inputs.put(AttributeConstants.DESIGNATION, (String) jsonObject.get(AttributeConstants.DESIGNATION));
    inputs.put(AttributeConstants.PHONE, (String) jsonObject.get(AttributeConstants.PHONE));
    return inputs;
  }

  private void createProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DataException {
    Employee employee = null;
    try {
      Map<String, String> inputs = setEmployeeAttributes(request);
      EmployeeFactory employeeFactory = new EmployeeFactory();
      employee = employeeFactory.createObject(inputs);
      validator.validate(employee);
      employeeService.create(employee);
      request.setAttribute(AttributeConstants.MESSAGE, AppConstants.EMPLOYEE_CREATED);
      request.setAttribute(AttributeConstants.EMPLOYEE, employee);
      response.sendRedirect(request.getContextPath() + UrlConstants.EMPLOYEE_ROUTE + UrlConstants.PATH_SEPARATOR + employee.getId());
    }
    catch (ValidationException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      request.setAttribute(AttributeConstants.ERRORS, e.getErrors());
      request.setAttribute(AttributeConstants.EMPLOYEE, employee);
      request.getRequestDispatcher(request.getContextPath() + UrlConstants.EMPLOYEE_CREATE_PAGE).forward(request, response);
    }
  }

  private void createProcess2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DataException {
    Employee employee = null;
    try {
      Map<String, String> inputs = setEmployeeAttributesFromJSON(request);
      EmployeeFactory employeeFactory = new EmployeeFactory();
      employee = employeeFactory.createObject(inputs);
      validator.validate(employee);
      employeeService.create(employee);
      request.setAttribute(AttributeConstants.MESSAGE, AppConstants.EMPLOYEE_CREATED);
      request.setAttribute(AttributeConstants.EMPLOYEE, employee);
      response.sendRedirect(request.getContextPath() + UrlConstants.EMPLOYEE_ROUTE + UrlConstants.PATH_SEPARATOR + employee.getId());
      //    response.getWriter().write("lala");

    }
    catch (ValidationException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      request.setAttribute(AttributeConstants.ERRORS, e.getErrors());
      request.setAttribute(AttributeConstants.EMPLOYEE, employee);
      request.getRequestDispatcher(request.getContextPath() + UrlConstants.EMPLOYEE_CREATE_PAGE).forward(request, response);
    }
  }

  private void editProcess(HttpServletRequest request, HttpServletResponse response) throws DataException, IOException, ServletException {
    Employee employee = null;
    try {
      int id = parameterValueAsInt(request, 2);
      Map<String, String> inputs = setEmployeeAttributes(request);
      EmployeeFactory employeeFactory = new EmployeeFactory();
      employee = employeeFactory.createObject(inputs);
      employee.setId(id);
      validator.validate(employee);
      employeeService.edit(employee);
      request.setAttribute(AttributeConstants.MESSAGE, AppConstants.EMPLOYEE_UPDATED);
      request.setAttribute(AttributeConstants.EMPLOYEE, employee);
      response.sendRedirect(request.getContextPath() + UrlConstants.EMPLOYEE_ROUTE + UrlConstants.PATH_SEPARATOR + employee.getId());
    }
    catch (ValidationException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      request.setAttribute(AttributeConstants.ERRORS, e.getErrors());
      request.setAttribute(AttributeConstants.EMPLOYEE, employee);
      request.getRequestDispatcher(request.getContextPath() + UrlConstants.EMPLOYEE_EDIT_PAGE).forward(request, response);
    }
  }

  private void deleteProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, DataException {
    int id = parameterValueAsInt(request, 2);
    employeeService.delete(id);
    response.sendRedirect(request.getContextPath() + UrlConstants.EMPLOYEE_ROUTE);
  }

  private void fetch(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    try {
      Integer pageLimit = AppConstants.PAGE_LIMIT;
      Integer pageNo = getCurrentPage(request);
      Integer offset = (pageNo-1)*pageLimit;

      List<Employee> employeeList = employeeService.fetch(pageLimit, offset);
      Integer employeeCount = employeeService.count();
      request.setAttribute(AttributeConstants.EMPLOYEE_LIST, employeeList);
      request.setAttribute(AttributeConstants.EMPLOYEE_COUNT, employeeCount);
      request.setAttribute(AttributeConstants.PAGE_NO, pageNo);
      if ((employeeCount % pageLimit) == 0) {
        request.setAttribute(AttributeConstants.LAST_PAGE_NO, employeeCount / pageLimit);
      }
      else {
        request.setAttribute(AttributeConstants.LAST_PAGE_NO, (employeeCount / pageLimit) + 1);
      }
      request.getServletContext().getRequestDispatcher(request.getContextPath() + UrlConstants.EMPLOYEE_LIST_PAGE).forward(request, response);
    }
    catch (Exception e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new ServletException(AppConstants.INTERNAL_SERVER_ERROR);
    }
  }

  private void view(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    try {
      int id = parameterValueAsInt(request, 2);
      Employee employee = employeeService.fetchById(id);
      if (employee != null) {
        request.setAttribute(AttributeConstants.EMPLOYEE, employee);
        request.getRequestDispatcher(request.getContextPath() + UrlConstants.EMPLOYEE_VIEW_PAGE).forward(request, response);
      }
      else {
        throw new ServletException(AppConstants.PAGE_NOT_FOUND_MESSAGE);
      }
    }
    catch (NumberFormatException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new ServletException(AppConstants.PAGE_NOT_FOUND_MESSAGE);
    }
    catch (Exception e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      request.setAttribute(AttributeConstants.MESSAGE, e.getMessage());
      throw new ServletException(AppConstants.INTERNAL_SERVER_ERROR);
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

  private String getAction(HttpServletRequest request) {
    String[] parts = getPathParams(request);
    String action;

    if (parts.length == 2) {
      action = AppConstants.FETCH;
    }
    else if (parts.length == 3 && AppConstants.CREATE.equals(parts[2])) {
      action = AppConstants.CREATE;
    }
    else if (parts.length == 3 && AppConstants.CREATE2.equals(parts[2])) {
      action = AppConstants.CREATE2;
    }
    else if (parts.length == 3) {
      action = AppConstants.VIEW;
    }
    else if (parts.length == 4 && AppConstants.EDIT.equals(parts[3])) {
      action = AppConstants.EDIT;
    }
    else {
      action = null;
    }
    return action;
  }

  private String postAction(HttpServletRequest request) {
    String[] parts = getPathParams(request);
    String action;
    if (parts.length == 3 && AppConstants.CREATE.equals(parts[2])) {
      action = AppConstants.CREATE;
    }
    else if (parts.length == 3 && AppConstants.CREATE2.equals(parts[2])) {
      action = AppConstants.CREATE2;
    }
    else if (parts.length == 4 && AppConstants.EDIT.equals(parts[3])) {
      action = AppConstants.EDIT;
    }
    else if (parts.length == 4 && AppConstants.DELETE.equals(parts[3])) {
      action = AppConstants.DELETE;
    }
    else {
      action = null;
    }
    return action;
  }
}
