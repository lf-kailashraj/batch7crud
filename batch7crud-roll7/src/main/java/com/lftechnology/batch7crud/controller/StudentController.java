package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.exception.ValidatorException;
import com.lftechnology.batch7crud.factory.StudentFactory;
import com.lftechnology.batch7crud.model.Student;
import com.lftechnology.batch7crud.service.StudentServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Prajjwal Raj Kandel <prajjwalkandel@lftechnology.com> on 1/18/16.
 */
@WebServlet(name = "StudentController", urlPatterns = { "/students/*" })
public class StudentController extends CommonHttpServlet{
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
        showNotFoundErrorPage(request, response);
    } catch (DataException | ServletException | IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      showInternalErrorPage(request, response);
    }

  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String[] urlPath = urlParts(request);
    try {
      if (urlPath.length == 2 && CommonConstants.LIST_URL.equals(urlPath[1]))
        list(request, response);
      else if (urlPath.length == 3 && CommonConstants.NEW_ENTRY.equals(urlPath[2]))
        create(request, response);
      else if (urlPath.length == 4 && CommonConstants.EDIT.equals(urlPath[3]))
        edit(request, response, Integer.parseInt(urlPath[2]));
      else if (urlPath.length == 3)
        show(request, response);
      else{
        showNotFoundErrorPage(request, response);
      }
    } catch (DataException | ServletException | IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      showInternalErrorPage(request, response);
    } catch (NumberFormatException e){
      showNotFoundErrorPage(request, response);
    }

  }

  private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DataException {
    int pageSize = CommonConstants.PAGE_SIZE;
    int page = 1;
    try {
      page = getPageNumber(request);
    } catch (NumberFormatException e) {
      showNotFoundErrorPage(request, response);
    }
    int totalStudents = studentService.fetchTotal();
    int numberOfPages = (int) Math.ceil(totalStudents / (float) pageSize);

    if (page != 1 && page > numberOfPages ) {
      page = 1;
    }
    request.setAttribute("students", studentService.fetch(pageSize, (page - 1) * pageSize));
    request.setAttribute("pageSize", pageSize);
    request.setAttribute("totalStudents", totalStudents);
    request.setAttribute("pageNum", page);
    request.setAttribute("numberOfPages",numberOfPages);
    request.getServletContext().getRequestDispatcher(CommonConstants.STUDENTS_LIST_VIEW).forward(request, response);
  }

  private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getServletContext().getRequestDispatcher(CommonConstants.NEW_ENTRY_VIEW).forward(request, response);
  }

  private void createProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DataException {

    Map<String,String> studentMap = createHashMapFromInputs(request);

    try {
      StudentFactory studentFactory = new StudentFactory();
      Student student = studentFactory.createObject(studentMap);
      studentService.addNew(student);
      response.sendRedirect(request.getContextPath() + "/" + CommonConstants.LIST_URL);
    } catch (ValidatorException e) {
      LOGGER.log(Level.SEVERE,e.getMessage(),e);
      request.setAttribute("error",e.getErrors());
      request.getServletContext().getRequestDispatcher(CommonConstants.NEW_ENTRY_VIEW).forward(request, response);
    }

  }

  private void edit(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException, DataException {
    if(studentService.fetchById(id) == null) {
      showNotFoundErrorPage(request, response);
      return;
    }
    request.setAttribute("student", studentService.fetchById(id));
    request.getRequestDispatcher(CommonConstants.EDIT_ENTRY_VIEW).forward(request, response);

  }

  private void editProcess(HttpServletRequest request, HttpServletResponse response, int id)
    throws ServletException, IOException, DataException {

    Map<String,String> studentMap = createHashMapFromInputs(request);
    Student student = null;

    try {
      StudentFactory studentFactory = new StudentFactory();
      student = studentFactory.createObject(studentMap);
      student.setId(id);

      request.setAttribute("student", student);
      studentService.update(student);
      response.sendRedirect(request.getContextPath() + "/" + CommonConstants.LIST_URL);
    } catch (ValidatorException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      request.setAttribute("error", e.getErrors());

      request.setAttribute("student", studentService.fetchById(id));
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
        showNotFoundErrorPage(request, response);
      else {
        request.setAttribute("student", student);
        request.getRequestDispatcher(CommonConstants.SHOW_STUDENT_VIEW).forward(request, response);
      }
    } catch (NumberFormatException e) {
      showNotFoundErrorPage(request, response);
    }

  }

  private Map<String,String> createHashMapFromInputs(HttpServletRequest request){
    String name = request.getParameter("name");
    String address = request.getParameter("address");
    String roll = request.getParameter("roll");
    Map<String,String > studentMap = new HashMap<>();
    studentMap.put("name",name);
    studentMap.put("roll",roll);
    studentMap.put("address",address);
    return studentMap;
  }

}
