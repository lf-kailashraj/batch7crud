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
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by sanjay on 1/18/16.
 */
@WebServlet("/students/*")
public class StudentController extends HTTPStatusHandler {
  private static final Logger LOGGER = Logger.getLogger(StudentController.class.getName());
  private static StudentService studentService = new StudentService();

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
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
    } catch(ServletException | IOException e){
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      show500(request, response, e);
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String[] parameters = parameterValues(request);
    try{
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
    }
    catch(ServletException | IOException e){
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      show500(request, response, e);
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
      show500(request, response, e);
    } catch(NumberFormatException e){
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
      show500(request, response, e);
    } catch(NumberFormatException e){
      show404(request, response);
    }
  }

  private void createProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Student student = new Student();
    student.setFirstName(request.getParameter(ParameterConstant.FIRST_NAME));
    student.setMiddleName(request.getParameter(ParameterConstant.MIDDLE_NAME));
    student.setLastName(request.getParameter(ParameterConstant.LAST_NAME));
    student.setAddress(request.getParameter(ParameterConstant.ADDRESS));
    student.setGrade(Integer.parseInt(request.getParameter(ParameterConstant.GRADE)));
    try {
      studentService.save(student);
      response.sendRedirect(request.getContextPath());
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      show404(request, response);
    } catch(NumberFormatException e){
      show404(request, response);
    }
  }

  private void editProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      int studentId = parameterValueAsInt(request, 2);
      Student student = new Student();
      student.setId(studentId);
      student.setFirstName(request.getParameter(ParameterConstant.FIRST_NAME));
      student.setMiddleName(request.getParameter(ParameterConstant.MIDDLE_NAME));
      student.setLastName(request.getParameter(ParameterConstant.LAST_NAME));
      student.setAddress(request.getParameter(ParameterConstant.ADDRESS));
      student.setGrade(Integer.parseInt(request.getParameter(ParameterConstant.GRADE)));
      studentService.edit(student);
      response.sendRedirect(request.getContextPath() + File.separator + UrlConstant.STUDENTS);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      show404(request, response);
    } catch(NumberFormatException e){
      show404(request, response);
    }
  }

  private void deleteProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      int studentId = parameterValueAsInt(request, 2);
      studentService.delete(studentId);
      response.sendRedirect(request.getContextPath() + File.separator + UrlConstant.STUDENTS);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      show500(request, response, e);
    } catch(NumberFormatException e){
      show404(request, response);
    }
  }
}
