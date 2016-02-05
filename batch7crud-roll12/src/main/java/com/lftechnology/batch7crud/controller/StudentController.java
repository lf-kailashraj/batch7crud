package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constants.*;
import com.lftechnology.batch7crud.model.Student;
import com.lftechnology.batch7crud.parser.UrlParser;
import com.lftechnology.batch7crud.service.StudentServiceImpl;
import com.lftechnology.batch7crud.validator.FormValidatorImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.List;
import java.util.logging.Level;
import java.io.File;

/**
 * Created by sagarmatha on 1/27/16.
 */

@WebServlet("/students/*")
public class StudentController extends CommonHTTPRequestHandler {
  private static final Logger logger = Logger.getLogger(StudentController.class.getName());
  private static StudentServiceImpl studentServiceImpl = new StudentServiceImpl();
  private static Map<String, String> errorMessage = new HashMap<String, String>();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    UrlParser parser = new UrlParser();
    int action = parser.parse(req);
    try {
      switch (action) {
        case 1:
          listStudents(req, res);
          break;
        case 2:
          redirectToCreate(req, res);
          break;
        case 3:
          redirectToUpdate(req, res);
          break;
        case 4:
          viewStudentDetail(req, res);
          break;
        default:
          show404(req, res);
      }
    } catch (ServletException e) {
      logger.log(Level.SEVERE, e.getMessage(), e);
      show500(req, res);
    } catch (IOException e) {
      logger.log(Level.SEVERE, e.getMessage(), e);
      show500(req, res);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    req.setAttribute("errorMessage", errorMessage);
    UrlParser parser = new UrlParser();
    int action = parser.parse(req);
    try {
      switch (action) {
        case 2:
          createStudent(req, res);
          break;
        case 3:
          updateStudent(req, res);
          break;
        case 5:
          deleteStudent(req, res);
          break;
        default:
          show404(req, res);
      }
    } catch (ServletException e) {
      logger.log(Level.SEVERE, e.getMessage(), e);
      show500(req, res);
    } catch (IOException e) {
      logger.log(Level.SEVERE, e.getMessage(), e);
      show500(req, res);
    }
  }

  private void redirectToCreate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    req.getServletContext().getRequestDispatcher(PageConstants.NEW_STUDENT).forward(req, res);
  }

  private void listStudents(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    List<Student> stdList;
    try {
      int page = pageNumber(req);
      stdList = studentServiceImpl.viewList(page, NumberConstants.LIMIT);
      int totalCount = studentServiceImpl.countStudents();
      req.setAttribute(AttributeConstants.STUDENT_LIST, stdList);
      req.setAttribute(AttributeConstants.PAGE, page);
      req.setAttribute(AttributeConstants.TOTAL_PAGES, Math.ceil(totalCount / (double) NumberConstants.LIMIT));
      req.getServletContext().getRequestDispatcher(PageConstants.LIST_VIEW).forward(req, res);
    } catch (NumberFormatException e) {
      show404(req, res);
    }
  }

  private void viewStudentDetail(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    try {
      String urlPath = req.getRequestURI().substring(req.getContextPath().length());
      int studentId = Integer.parseInt(urlPath.split(File.separator)[2]);
      if (studentServiceImpl.findRecord(studentId)) {
        Student std = studentServiceImpl.viewDetail(studentId);
        int totalStudents = studentServiceImpl.countStudents();
        req.setAttribute(AttributeConstants.STUDENT, std);
        req.setAttribute(AttributeConstants.CURRENT_STUDENT, studentId);
        req.setAttribute(AttributeConstants.TOTAL_STUDENTS, totalStudents);
        req.getServletContext().getRequestDispatcher(PageConstants.DETAIL_VIEW).forward(req, res);
      } else {
        req.getServletContext().getRequestDispatcher(PageConstants.INDEX_PAGE).forward(req, res);
      }

    } catch (ServletException e) {
      logger.log(Level.SEVERE, e.getMessage(), e);
      show404(req, res);
    } catch (IOException e) {
      logger.log(Level.SEVERE, e.getMessage(), e);
      show404(req, res);
    }
  }

  private void redirectToUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    try {
      int studentId = parameterValueAsInt(req, 2);
      if (studentServiceImpl.findRecord(studentId)) {
        req.setAttribute(AttributeConstants.STUDENT, studentServiceImpl.viewDetail(studentId));
        RequestDispatcher view = req.getRequestDispatcher(PageConstants.UPDATE);
        view.forward(req, res);
      } else {
        req.getServletContext().getRequestDispatcher(PageConstants.INDEX_PAGE).forward(req, res);
      }
    } catch (IOException e) {
      logger.log(Level.SEVERE, e.getMessage(), e);
      show404(req, res);
    }
  }

  private void createStudent(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    FormValidatorImpl validate = new FormValidatorImpl(errorMessage);
    Boolean statusCheck;
    Map<String, String> data = new HashMap<String, String>();
    data.put("firstName", req.getParameter(ParameterConstants.FIRST_NAME));
    data.put("lastName", req.getParameter(ParameterConstants.LAST_NAME));
    data.put("age", req.getParameter(ParameterConstants.AGE));
    data.put("address", req.getParameter(ParameterConstants.ADDRESS));
    statusCheck = validate.validateInput(data);
    if (statusCheck) {
      Student student = new Student();
      student.setFirstName(req.getParameter(ParameterConstants.FIRST_NAME));
      student.setLastName(req.getParameter(ParameterConstants.LAST_NAME));
      student.setAge(Integer.parseInt(req.getParameter(ParameterConstants.AGE)));
      student.setAddress(req.getParameter(ParameterConstants.ADDRESS));
      studentServiceImpl.insert(student);
      res.sendRedirect(req.getContextPath());
    } else {
      req.getServletContext().getRequestDispatcher(PageConstants.NEW_STUDENT).forward(req, res);
    }
  }

  private void updateStudent(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    FormValidatorImpl validate = new FormValidatorImpl(errorMessage);
    Boolean statusCheck;
    Map<String, String> data = new HashMap<String, String>();
    data.put("firstName", req.getParameter(ParameterConstants.FIRST_NAME));
    data.put("lastName", req.getParameter(ParameterConstants.LAST_NAME));
    data.put("age", req.getParameter(ParameterConstants.AGE));
    data.put("address", req.getParameter(ParameterConstants.ADDRESS));
    statusCheck = validate.validateInput(data);
    if (statusCheck) {
      try {
        String urlPath = req.getRequestURI().substring(req.getContextPath().length());
        int studentId = Integer.parseInt(urlPath.split(File.separator)[2]);
        Student student = new Student();
        student.setStudentID(studentId);
        student.setFirstName(req.getParameter(ParameterConstants.FIRST_NAME));
        student.setLastName(req.getParameter(ParameterConstants.LAST_NAME));
        student.setAge(Integer.parseInt(req.getParameter(ParameterConstants.AGE)));
        student.setAddress(req.getParameter(ParameterConstants.ADDRESS));
        studentServiceImpl.update(student);
        res.sendRedirect(req.getContextPath() + File.separator + UrlConstants.STUDENTS);
      } catch (NumberFormatException e) {
        show404(req, res);
      }
    } else {
      req.getServletContext().getRequestDispatcher(PageConstants.NEW_STUDENT).forward(req, res);
    }
  }

  private void deleteStudent(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    try {
      String urlPath = req.getRequestURI().substring(req.getContextPath().length());
      int studentId = Integer.parseInt(urlPath.split(File.separator)[2]);
      studentServiceImpl.delete(studentId);
      res.sendRedirect(req.getContextPath() + File.separator + UrlConstants.STUDENTS);
    } catch (NumberFormatException e) {
      show404(req, res);
    }
  }
}
