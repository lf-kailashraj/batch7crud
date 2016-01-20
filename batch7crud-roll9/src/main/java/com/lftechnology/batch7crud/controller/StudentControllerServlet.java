package com.lftechnology.batch7crud.controller;

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
  private static final Logger LOGGER = Logger.getLogger("appLogger");
  private static StudentService studentService = new StudentService();

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String[] parameters = parameterValues(request);

    if (parameters.length == 3 && "create".equals(parameters[2])) {
      createProcess(request, response);
    } else if (parameters.length == 4 && "edit".equals(parameters[3])) {
      editProcess(request, response);
    } else if (parameters.length == 4 && "delete".equals(parameters[3])) {
      deleteProcess(request, response);
    } else {
      show404(request, response);
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String[] parameters = parameterValues(request);

    if (parameters.length == 2 && "students".equals(parameters[1])) {
      list(request, response);
    } else if (parameters.length == 3 && "create".equals(parameters[2])) {
      create(request, response);
    } else if (parameters.length == 4 && "edit".equals(parameters[3])) {
      edit(request, response);
    } else if (parameters.length == 4 && "view".equals(parameters[3])) {
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
      request.setAttribute("student", std);
      request.setAttribute("currentStudent", studentId);
      request.setAttribute("totalStudent", totalStudent);
      request.getServletContext().getRequestDispatcher("/WEB-INF/views/detail-view.jsp").forward(request, response);
    } catch (DataException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      show500(request, response, ex);
    }
  }

  private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Student> stdList;
    try {
      int limit = 20;
      String forward = "/WEB-INF/views/list-view.jsp";

      int page = pageNumber(request);
      stdList = studentService.fetch(page, limit);
      int totalCount = studentService.studentCount();
      request.setAttribute("studentList", stdList);
      request.setAttribute("page", page);
      request.setAttribute("totalPages", Math.ceil(totalCount / (double) limit));
      request.getServletContext().getRequestDispatcher(forward).forward(request, response);
    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      show404(request, response);
    }
  }

  private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getServletContext().getRequestDispatcher("/WEB-INF/views/newStudent.jsp").forward(request, response);
  }

  private void createProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Student s = new Student();
    s.setFirstName(request.getParameter("fname"));
    s.setMiddleName(request.getParameter("mname"));
    s.setLastName(request.getParameter("lname"));
    s.setAddress(request.getParameter("address"));
    s.setGrade(Integer.parseInt(request.getParameter("grade")));
    try {
      studentService.save(s);
      response.sendRedirect(request.getContextPath());
    } catch (DataException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      show404(request, response);
    }
  }

  private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      int studentId = parameterValueAsInt(request, 2);
      String forward = "/WEB-INF/views/edit.jsp";
      request.setAttribute("student", studentService.fetchById(studentId));
      RequestDispatcher view = request.getRequestDispatcher(forward);
      view.forward(request, response);
    } catch (DataException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      show500(request, response, ex);
    }
  }

  private void editProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      int studentId = parameterValueAsInt(request, 2);
      Student s = new Student();
      s.setId(studentId);
      s.setFirstName(request.getParameter("fname"));
      s.setMiddleName(request.getParameter("mname"));
      s.setLastName(request.getParameter("lname"));
      s.setAddress(request.getParameter("address"));
      s.setGrade(Integer.parseInt(request.getParameter("grade")));
      studentService.edit(s);
      response.sendRedirect(request.getContextPath() + "/students");
    } catch (DataException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
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
    }
  }
}
