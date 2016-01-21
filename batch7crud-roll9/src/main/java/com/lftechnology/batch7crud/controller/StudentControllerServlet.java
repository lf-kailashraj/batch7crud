package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constants.*;
import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.services.StudentService;

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
 * Created by sanjay on 1/18/16.
 */
@WebServlet("/students/*")
public class StudentControllerServlet extends HTTPStatusHandler {
  private static final Logger LOGGER = Logger.getLogger(StudentControllerServlet.class.getName());
  private static StudentService studentService = new StudentService();

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String[] parameters = parameterValues(request);

    if (parameters.length == 3 && Url.CREATE.equals(parameters[2])) {
      createProcess(request, response);
    } else if (parameters.length == 4 && Url.EDIT.equals(parameters[3])) {
      editProcess(request, response);
    } else if (parameters.length == 4 && Url.DELETE.equals(parameters[3])) {
      deleteProcess(request, response);
    } else {
      show404(request, response);
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String[] parameters = parameterValues(request);
    if (parameters.length == 2 && Url.STUDENTS.equals(parameters[1])) {
      list(request, response);
    } else if (parameters.length == 3 && Url.CREATE.equals(parameters[2])) {
      create(request, response);
    } else if (parameters.length == 4 && Url.EDIT.equals(parameters[3])) {
      edit(request, response);
    } else if (parameters.length == 4 && Url.VIEW.equals(parameters[3])) {
      view(request, response);
    } else {
      show404(request, response);
    }
  }

  private void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      int studentId = parameterValueAsInt(request, 2);
      Student std = studentService.fetchById(studentId);
      int totalStudent = studentService.studentCount();
      request.setAttribute(Attribute.STUDENT, std);
      request.setAttribute(Attribute.CURRENT_STUDENT, studentId);
      request.setAttribute(Attribute.TOTAL_STUDENT, totalStudent);
      request.getServletContext().getRequestDispatcher(Page.DETAIL_VIEW).forward(request, response);
    } catch (DataException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      show500(request, response, ex);
    } catch(NumberFormatException ex){
      show404(request, response);
    }
  }

  private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Student> stdList;
    try {
      int page = pageNumber(request);
      stdList = studentService.fetch(page, Numbers.LIMIT);
      int totalCount = studentService.studentCount();
      request.setAttribute(Attribute.STUDENT_LIST, stdList);
      request.setAttribute(Attribute.PAGE, page);
      request.setAttribute(Attribute.TOTAL_PAGE, Math.ceil(totalCount / (double) Numbers.LIMIT));
      request.getServletContext().getRequestDispatcher(Page.LIST_VIEW).forward(request, response);
    } catch (DataException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      show404(request, response);
    } catch (NumberFormatException ex) {
      show404(request, response);
    }
  }

  private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getServletContext().getRequestDispatcher(Page.NEW_STUDENT).forward(request, response);
  }

  private void createProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Student s = new Student();
    s.setFirstName(request.getParameter(Parameter.FIRST_NAME));
    s.setMiddleName(request.getParameter(Parameter.MIDDLE_NAME));
    s.setLastName(request.getParameter(Parameter.LAST_NAME));
    s.setAddress(request.getParameter(Parameter.ADDRESS));
    s.setGrade(Integer.parseInt(request.getParameter(Parameter.GRADE)));
    try {
      studentService.save(s);
      response.sendRedirect(request.getContextPath());
    } catch (DataException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      show404(request, response);
    } catch(NumberFormatException ex){
      show404(request, response);
    }
  }

  private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      int studentId = parameterValueAsInt(request, 2);
      request.setAttribute(Attribute.STUDENT, studentService.fetchById(studentId));
      RequestDispatcher view = request.getRequestDispatcher(Page.EDIT);
      view.forward(request, response);
    } catch (DataException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      show500(request, response, ex);
    } catch(NumberFormatException ex){
      show404(request, response);
    }

  }

  private void editProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      int studentId = parameterValueAsInt(request, 2);
      Student s = new Student();
      s.setId(studentId);
      s.setFirstName(request.getParameter(Parameter.FIRST_NAME));
      s.setMiddleName(request.getParameter(Parameter.MIDDLE_NAME));
      s.setLastName(request.getParameter(Parameter.LAST_NAME));
      s.setAddress(request.getParameter(Parameter.ADDRESS));
      s.setGrade(Integer.parseInt(request.getParameter(Parameter.GRADE)));
      studentService.edit(s);
      response.sendRedirect(request.getContextPath() + "/students");
    } catch (DataException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      show404(request, response);
    } catch(NumberFormatException ex){
      show404(request, response);
    }
  }

  private void deleteProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      int studentId = parameterValueAsInt(request, 2);
      studentService.delete(studentId);
      response.sendRedirect(request.getContextPath() + "/students");
    } catch (DataException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      show500(request, response, ex);
    } catch(NumberFormatException ex){
      show404(request, response);
    }
  }
}
