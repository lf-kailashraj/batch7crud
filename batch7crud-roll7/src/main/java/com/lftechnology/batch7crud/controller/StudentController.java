package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.exception.ValidatorException;
import com.lftechnology.batch7crud.factory.StudentFactory;
import com.lftechnology.batch7crud.model.Student;
import com.lftechnology.batch7crud.service.StudentServices;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Prajjwal Raj Kandel <prajjwalkandel@lftechnology.com> on 1/18/16.
 */
@WebServlet(name = "StudentController", urlPatterns = { "/students/*" })
public class StudentController extends CommonHttpServlet {
  private static final Logger LOGGER = Logger.getLogger(StudentController.class.getName());

  private static StudentServices studentService = new StudentServices();

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String[] urlPath = urlParts(request);

    try {
      if (urlPath.length == 3 && CommonConstants.NEW_ENTRY.equals(urlPath[2]))
        createProcess(request, response);
      else if (urlPath.length == 4 && CommonConstants.EDIT.equals(urlPath[3]))
        editProcess(request, response, Integer.parseInt(urlPath[2]));
      else if (urlPath.length == 4 && CommonConstants.DELETE.equals(urlPath[3]))
        deleteProcess(request, response, Integer.parseInt(urlPath[2]));
      else
        throw new ServletException(CommonConstants.PAGE_NOT_FOUND);
    } catch (DataException | IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new ServletException(CommonConstants.INTERNAL_SERVER_ERROR); // NOSONAR
    }

  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      getProcess(request, response);
    } catch (DataException | IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new ServletException(CommonConstants.INTERNAL_SERVER_ERROR); // NOSONAR
    } catch (NumberFormatException e) {
      throw new ServletException(CommonConstants.PAGE_NOT_FOUND); // NOSONAR
    }
  }

  private void getProcess(HttpServletRequest request, HttpServletResponse response) throws DataException, IOException, ServletException {
    String[] urlPath = urlParts(request);
    if (urlPath.length == 2 && CommonConstants.LIST_URL.equals(urlPath[1]))
      list(request, response);
    else if (urlPath.length == 3 && CommonConstants.NEW_ENTRY.equals(urlPath[2]))
      create(request, response);
    else if (urlPath.length == 4 && CommonConstants.EDIT.equals(urlPath[3]))
      edit(request, response, Integer.parseInt(urlPath[2]));
    else if (urlPath.length == 3)
      show(request, response);
    else
      throw new ServletException(CommonConstants.PAGE_NOT_FOUND);
  }

  private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DataException {
    int pageSize = CommonConstants.PAGE_SIZE;
    int page = 1;
    try {
      page = getPageNumber(request);
    } catch (NumberFormatException e) {
      throw new ServletException(CommonConstants.PAGE_NOT_FOUND);
    }
    int totalStudents = studentService.fetchTotal();
    int numberOfPages = (int) Math.ceil(totalStudents / (float) pageSize);

    if (page != 1 && page > numberOfPages) {
      page = 1;
    }
    request.setAttribute("students", studentService.fetch(pageSize, (page - 1) * pageSize));
    request.setAttribute("pageSize", pageSize);
    request.setAttribute("totalStudents", totalStudents);
    request.setAttribute("pageNum", page);
    request.setAttribute("numberOfPages", numberOfPages);
    request.getServletContext().getRequestDispatcher(CommonConstants.STUDENTS_LIST_VIEW).forward(request, response);
  }

  private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getServletContext().getRequestDispatcher(CommonConstants.NEW_ENTRY_VIEW).forward(request, response);
  }

  private void createProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DataException {

    BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
    String json = reader.readLine();
    JSONObject jsonObject = new JSONObject(json);
    Map<String, String> inputs = new HashMap<>();
    inputs.put("name", jsonObject.getString("name"));
    inputs.put("address", jsonObject.getString("address"));
    inputs.put("roll", jsonObject.getString("roll"));

    try {
      StudentFactory studentFactory = new StudentFactory();
      Student student = studentFactory.createObject(inputs);
      studentService.addNew(student);

    } catch (ValidatorException e) {
      String jsonError = JSONObject.valueToString(e.getErrors());
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      response.getWriter().write(jsonError);
      LOGGER.log(Level.SEVERE, e.getMessage(), e);

    }
  }

  private void edit(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException, DataException {
    if (studentService.fetchById(id) == null) {
      throw new ServletException(CommonConstants.PAGE_NOT_FOUND);
    }
    request.setAttribute("student", studentService.fetchById(id));
    request.getRequestDispatcher(CommonConstants.EDIT_ENTRY_VIEW).forward(request, response);

  }

  private void editProcess(HttpServletRequest request, HttpServletResponse response, int id)
    throws ServletException, IOException, DataException {

    Map<String, String> studentMap = createHashMapFromInputs(request);
    studentMap.put("id", String.valueOf(id));
    Student student = new Student();

    try {
      StudentFactory studentFactory = new StudentFactory();
      student = studentFactory.createObject(studentMap);
      student.setId(id);

      request.setAttribute("student", studentMap);
      studentService.update(student);
      response.sendRedirect(request.getContextPath() + "/" + CommonConstants.LIST_URL);
    } catch (ValidatorException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      request.setAttribute("error", e.getErrors());
      request.setAttribute("student", studentMap);
      request.getServletContext().getRequestDispatcher(CommonConstants.EDIT_ENTRY_VIEW).forward(request, response);
    }

  }

  private void deleteProcess(HttpServletRequest request, HttpServletResponse response, int id)
    throws ServletException, IOException, DataException {
    studentService.delete(id);
    response.sendRedirect(request.getContextPath() + "/" + CommonConstants.LIST_URL);

  }

  private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DataException {
    try {
      int id = urlInteger(request, 2);
      Student student = studentService.fetchById(id);
      if (student == null)
        throw new ServletException(CommonConstants.PAGE_NOT_FOUND);
      else {
        request.setAttribute("student", student);
        request.getRequestDispatcher(CommonConstants.SHOW_STUDENT_VIEW).forward(request, response);
      }
    } catch (NumberFormatException e) {
      throw new ServletException(CommonConstants.PAGE_NOT_FOUND);
    }

  }

  private Map<String, String> createHashMapFromInputs(HttpServletRequest request) {
    String name = request.getParameter("name");
    String address = request.getParameter("address");
    String roll = request.getParameter("roll");
    Map<String, String> studentMap = new HashMap<>();
    studentMap.put("name", name);
    studentMap.put("roll", roll);
    studentMap.put("address", address);
    return studentMap;
  }

}
