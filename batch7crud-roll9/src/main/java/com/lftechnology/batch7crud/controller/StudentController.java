package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constants.*;
import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.services.StudentService;
import com.lftechnology.batch7crud.util.StudentValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lftechnology.batch7crud.exception.ValidationException;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by sanjay on 1/18/16.
 */
@WebServlet("/students/*")
public class StudentController extends HTTPStatusHandler {
  private static final Logger LOGGER = Logger.getLogger(StudentController.class.getName());
  private static StudentService studentService = new StudentService();
  private static StudentValidator studentValidator = new StudentValidator();

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String[] parameters = parameterValues(request);
    try {
      if (parameters.length == 3 && UrlConstant.CREATE.equals(parameters[2])) {
        createProcess(request, response);
      } else if (parameters.length == 4 && UrlConstant.EDIT.equals(parameters[3])) {
        editProcess(request, response);
      } else if (parameters.length == 4 && UrlConstant.DELETE.equals(parameters[3])) {
        deleteProcess(request, response);
      } else {
        show404(request, response);
      }
    } catch (ServletException | IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      show500(request, response);
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String[] parameters = parameterValues(request);
    try {
      if (parameters.length == 2 && UrlConstant.STUDENTS.equals(parameters[1])) {
        list(request, response);
      } else if (parameters.length == 3 && UrlConstant.CREATE.equals(parameters[2])) {
        create(request, response);
      } else if (parameters.length == 4 && UrlConstant.EDIT.equals(parameters[3])) {
        edit(request, response);
      } else if (parameters.length == 4 && UrlConstant.VIEW.equals(parameters[3])) {
        view(request, response);
      } else {
        show404(request, response);
      }
    } catch (ServletException | IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      show500(request, response);
    }
  }

  private void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      int studentId = parameterValueAsInt(request, 2);
      Student std = studentService.fetchById(studentId);
      int totalStudent = studentService.studentCount();
      request.setAttribute(AttributeConstant.STUDENT, std);
      request.setAttribute(AttributeConstant.CURRENT_STUDENT, studentId);
      request.setAttribute(AttributeConstant.TOTAL_STUDENT, totalStudent);
      request.getServletContext().getRequestDispatcher(PageConstant.DETAIL_VIEW).forward(request, response);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      show500(request, response);
    } catch (NumberFormatException e) {
      show404(request, response);
    }
  }

  private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Student> stdList;
    try {
      int page = pageNumber(request);
      stdList = studentService.fetch(page, NumberConstant.LIMIT);
      int totalCount = studentService.studentCount();
      request.setAttribute(AttributeConstant.STUDENT_LIST, stdList);
      request.setAttribute(AttributeConstant.PAGE, page);
      request.setAttribute(AttributeConstant.TOTAL_PAGE, Math.ceil(totalCount / (double) NumberConstant.LIMIT));
      request.getServletContext().getRequestDispatcher(PageConstant.LIST_VIEW).forward(request, response);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      show404(request, response);
    } catch (NumberFormatException e) {
      show404(request, response);
    }
  }

  private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getServletContext().getRequestDispatcher(PageConstant.NEW_STUDENT).forward(request, response);
  }

  private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      int studentId = parameterValueAsInt(request, 2);
      request.setAttribute(AttributeConstant.STUDENT, studentService.fetchById(studentId));
      RequestDispatcher view = request.getRequestDispatcher(PageConstant.EDIT);
      view.forward(request, response);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      show500(request, response);
    } catch (NumberFormatException e) {
      show404(request, response);
    }
  }

  private void createProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Student student= null;
    try {
      Map<String, String> input = getParam(request);
      student = studentValidator.createObject(input);
      student = studentService.save(student);
      response.sendRedirect(request.getContextPath() + File.separator + UrlConstant.STUDENTS + File.separator + student.getId() + File.separator + UrlConstant.VIEW);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      show404(request, response);
    } catch (ValidationException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      request.setAttribute(AttributeConstant.USER_INFO_ERRORS, e.getErrors());
      request.setAttribute(AttributeConstant.STUDENT, student);
      create(request, response);
    }
  }

  private void editProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Student student = null;
    try {
      Map<String, String> input = getParam(request);
      student = studentValidator.createObject(input);
      student.setId(parameterValueAsInt(request, 2));
      student = studentService.edit(student);
      response.sendRedirect(request.getContextPath() + File.separator + UrlConstant.STUDENTS + File.separator + student.getId() + File.separator + UrlConstant.VIEW);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      show404(request, response);
    } catch (ValidationException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      request.setAttribute(AttributeConstant.USER_INFO_ERRORS, e.getErrors());
      request.setAttribute(AttributeConstant.STUDENT, student);
      RequestDispatcher view = request.getRequestDispatcher(PageConstant.EDIT);
      view.forward(request, response);
    }
  }

  private void deleteProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      int studentId = parameterValueAsInt(request, 2);
      studentService.delete(studentId);
      response.sendRedirect(request.getContextPath() + File.separator + UrlConstant.STUDENTS);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      show500(request, response);
    } catch (NumberFormatException e) {
      show404(request, response);
    }
  }

  private Map getParam(HttpServletRequest request) {
    Map<String, String> input = new HashMap();
    input.put(ParameterConstant.FIRST_NAME, request.getParameter(ParameterConstant.FIRST_NAME));
    input.put(ParameterConstant.MIDDLE_NAME, request.getParameter(ParameterConstant.MIDDLE_NAME));
    input.put(ParameterConstant.LAST_NAME, request.getParameter(ParameterConstant.LAST_NAME));
    input.put(ParameterConstant.ADDRESS, request.getParameter(ParameterConstant.ADDRESS));
    input.put(ParameterConstant.GRADE, request.getParameter(ParameterConstant.GRADE));
    return input;
  }
}
