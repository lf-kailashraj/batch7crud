package com.lftechnology.batch7crud.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.lftechnology.batch7crud.constant.CommonConstant.*;
import static com.lftechnology.batch7crud.constant.StudentConstant.*;
import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.service.StudentService;

@WebServlet("/students/*")
public class StudentController extends CustomHttpServlet {
  private static StudentService studentService = new StudentService();
  private static final Logger LOGGER = Logger.getLogger(StudentController.class.getName());

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    try {
      String[] parameters = parameterValues(request);

      if (parameters.length == 2) {
        list(request, response);
      } else if (parameters.length == 3 && (CREATE).equals(parameters[2])) {
        create(request, response);
      } else if (parameters.length == 4 && (EDIT).equals(parameters[3])) {
        edit(request, response);
      } else if (parameters.length == 3) {
        show(request, response);
      } else {
        show404(request, response);
      }

    } catch (DataException | IOException | ServletException e) {
      show500(request, response, e);

      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    try {
      String[] parameters = parameterValues(request);

      if (parameters.length == 3 && (CREATE).equals(parameters[2])) {
        createProcess(request, response);
      } else if (parameters.length == 4 && (EDIT).equals(parameters[3])) {
        editProcess(request, response);
      } else if (parameters.length == 4 && (DELETE).equals(parameters[3])) {
        deleteProcess(request, response);
      } else {
        show404(request, response);
      }

    } catch (DataException | IOException | ServletException e) {
      show500(request, response, e);
    }
  }

  private void list(HttpServletRequest request, HttpServletResponse response)
      throws DataException, ServletException, IOException {
    int pageSize = 3;
    int page = 1;

    try {
      page = getPageNumber(request);
    } catch (NumberFormatException e) {
      show404(request, response);
    }

    List<Student> studentList = studentService.fetch(page, pageSize);
    int count = studentService.fetchTotal();
    int numberOfPages = (int) Math.ceil(count / (float) pageSize);
    if (page != 1 && page > numberOfPages) {
      show404(request, response);
      return;
    }
    request.setAttribute(STUDENT_LIST, studentList);
    request.setAttribute(NUMBER_OF_PAGES, numberOfPages);
    request.setAttribute(PAGE_NUMBER, page);
    request.getRequestDispatcher(LIST_PAGE).forward(request, response);
  }

  private void show(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException, DataException {
    try {
      int id = parameterValueAsInt(request, 2);
      Student student = studentService.fetchStudentById(id);

      request.setAttribute(STUDENT, student);
      request.getRequestDispatcher(EDIT_PAGE).forward(request, response);
    } catch (NumberFormatException e) {
      show404(request, response);
    }
  }

  private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher(CREATE_PAGE).forward(request, response);
  }

  private void createProcess(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException, DataException {
    try {
      Student student = constructStudentFromRequest(request);
      studentService.insert(student);

      response.sendRedirect(request.getContextPath() + STUDENT_LIST_CONTROLLER);
    } catch (NumberFormatException e) {
      request.setAttribute(ROLL, request.getParameter(ROLL));
      request.setAttribute(NAME, request.getParameter(NAME));
      request.setAttribute(MESSAGE, INVALID_ROLL_MESSAGE);
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      request.getRequestDispatcher(CREATE_PAGE).forward(request, response);
    }
  }

  private Student constructStudentFromRequest(HttpServletRequest request) {
    String roll = request.getParameter(ROLL);
    String name = request.getParameter(NAME);

    Student student = new Student();
    student.setRoll(Integer.parseInt(roll));
    student.setName(name);
    return student;
  }

  private void edit(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException, DataException {
    try {
      int id = parameterValueAsInt(request, 2);
      Student student = studentService.fetchStudentById(id);

      request.setAttribute(STUDENT, student);
      request.getRequestDispatcher(EDIT_PAGE).forward(request, response);
    } catch (NumberFormatException e) {
      show404(request, response);
    }
  }

  private void editProcess(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException, DataException {
    Student student = new Student();
    try {
      int id = parameterValueAsInt(request, 2);
      student.setId(id);
      student = constructStudentFromRequest(request);

      studentService.edit(student);

      response.sendRedirect(request.getContextPath() + STUDENT_LIST_CONTROLLER);
    } catch (NumberFormatException e) {
      request.setAttribute(STUDENT, student);
      request.setAttribute(ROLL, request.getParameter(ROLL));
      request.setAttribute(NAME, request.getParameter(NAME));
      request.setAttribute(MESSAGE, INVALID_ROLL_MESSAGE);
      request.getRequestDispatcher(EDIT_PAGE).forward(request, response);
    }
  }

  private void deleteProcess(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException, DataException {
    int id = parameterValueAsInt(request, 2);
    studentService.delete(id);

    response.sendRedirect(request.getContextPath() + STUDENT_LIST_CONTROLLER);
  }
}
