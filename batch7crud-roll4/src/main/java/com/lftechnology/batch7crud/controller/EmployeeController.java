package com.lftechnology.batch7crud.controller;

import com.google.gson.Gson;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.factory.EmployeeFactory;
import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.service.EmployeeService;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.lftechnology.batch7crud.constant.ActionConstants.*;
import static com.lftechnology.batch7crud.constant.AttribConstants.*;
import static com.lftechnology.batch7crud.constant.MessageConstants.MESSAGE_INTERNAL_SERVER_ERROR;
import static com.lftechnology.batch7crud.constant.MessageConstants.MESSAGE_PAGE_NOT_FOUND;
import static com.lftechnology.batch7crud.constant.ParamConstants.*;
import static com.lftechnology.batch7crud.constant.UrlConstants.*;

/**
 * Created by Pratish Shrestha <pratishshrestha@lftechnology.com> on 1/14/16.
 */

@WebServlet(name = "EmployeeController", urlPatterns = { "/employees/*" })
public class EmployeeController extends CustomHttpServlet {
  private static final Logger LOGGER = Logger.getLogger(EmployeeController.class.getName());

  private EmployeeService employeeService;
  private EmployeeFactory employeeFactory;

  public EmployeeController() {
    employeeFactory = new EmployeeFactory();
    employeeService = new EmployeeService();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    validateUrl(request);
    String action = fetchActionFromParameter(request);

    switch (action) {
    case ACTION_LIST:
      list(request, response);
      break;

    case ACTION_CREATE:
      create(request, response);
      break;

    case ACTION_PROFILE:
      viewProfile(request, response);
      break;

    case ACTION_EDIT:
      edit(request, response);
      break;

    default:
      throw new ServletException(MESSAGE_PAGE_NOT_FOUND);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    validateUrl(request);
    String action = fetchActionFromParameter(request);

    switch (action) {
    case ACTION_CREATE:
      createProcess(request, response);
      break;

    case ACTION_DELETE:
      deleteProcess(request, response);
      break;

    case ACTION_EDIT:
      editProcess(request, response);
      break;

    default:
      throw new ServletException(MESSAGE_PAGE_NOT_FOUND);
    }

  }

  private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      int page = pageNumber(request);
      int noOfRecords = employeeService.fetchNoOfRecords();
      int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / ATTRIB_RECORD_LIMIT);

      if (page != 1 && page > noOfPages) {
        request.setAttribute(ATTRIB_MESSAGE, MESSAGE_PAGE_NOT_FOUND);
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
        return;
      }

      request.setAttribute(ATTRIB_EMPLOYEES, employeeService.fetch(page, ATTRIB_RECORD_LIMIT));
      request.setAttribute(ATTRIB_CURRENT_PAGE, page);
      request.setAttribute(ATTRIB_NO_OF_PAGES, noOfPages);

      RequestDispatcher view = request.getRequestDispatcher(URL_EMPLOYEE_LISTING_PAGE);
      view.forward(request, response);

    } catch (DataException | NumberFormatException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new ServletException(MESSAGE_INTERNAL_SERVER_ERROR);
    }

  }

  private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher view = request.getRequestDispatcher(URL_EMPLOYEE_CREATE_PAGE);
    view.forward(request, response);
  }

  private void createProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    Map<String, String> inputs = mapParameters(request);

    Employee employee = null;

    try {
      employee = employeeFactory.createObject(inputs);
      employeeService.save(employee);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      request.setAttribute(ATTRIB_MESSAGE, e.getMessage());
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    } catch (ValidationException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);

      String json = new Gson().toJson(e.getErrors());

      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      response.getWriter().write(json);
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
      throw new ServletException(MESSAGE_INTERNAL_SERVER_ERROR);
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
      throw new ServletException(MESSAGE_INTERNAL_SERVER_ERROR);
    }

  }

  private void editProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Map<String, String> inputs = mapParameters(request);

    int employeeId = parameterValueAsInt(request, 2);

    Employee employee = null;

    try {
      employee = employeeFactory.createObject(inputs);
      employee.setId(employeeId);

      employeeService.update(employee);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new ServletException(MESSAGE_INTERNAL_SERVER_ERROR);
    } catch (ValidationException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      String json = new Gson().toJson(e.getErrors());

      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      response.getWriter().write(json);
    }
  }

  private void deleteProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      int employeeId = parameterValueAsInt(request, 2);
      employeeService.deleteEmployee(employeeId);
      response.sendRedirect(request.getContextPath() + ACTION_EMPLOYEES);
    } catch (DataException | NumberFormatException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new ServletException(MESSAGE_INTERNAL_SERVER_ERROR);
    }
  }

  private Map<String, String> mapParameters(HttpServletRequest request) throws IOException {
    Map<String, String> inputs = new HashMap<String, String>();

    BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
    String json = "";

    if (reader != null) {
      json = reader.readLine();
    }

    JSONObject jsonObject = new JSONObject(json);

    inputs.put(PARAM_FIRST_NAME, (String) jsonObject.get("firstName"));
    inputs.put(PARAM_LAST_NAME, (String) jsonObject.get("lastName"));
    inputs.put(PARAM_STATION, (String) jsonObject.get("station"));
    return inputs;
  }

}