package com.lftechnology.batch7crud.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lftechnology.batch7crud.constant.CommonConstant;
import com.lftechnology.batch7crud.constant.PageConstant;
import com.lftechnology.batch7crud.constant.StudentConstant;
import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.service.StudentService;

@WebServlet("/students/*")
public class StudentController extends CustomHttpServlet {
  private static StudentService studentService = new StudentService();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    try {
      String[] parameters = parameterValues(request);

      if (parameters.length == 2) {
        list(request, response);
      } else if (parameters.length == 3 && (CommonConstant.CREATE).equals(parameters[2])) {
        create(request, response);
      } else if (parameters.length == 4 && (CommonConstant.EDIT).equals(parameters[3])) {
        edit(request, response);
      } else if (parameters.length == 3) {
        show(request, response);
      } else {
        show404(request, response);
      }

    } catch (DataException | IOException | ServletException e) {
      show500(request, response, e);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    try {
      String[] parameters = parameterValues(request);

      if (parameters.length == 3 && (CommonConstant.CREATE).equals(parameters[2])) {
        createProcess(request, response);
      } else if (parameters.length == 4 && (CommonConstant.EDIT).equals(parameters[3])) {
        editProcess(request, response);
      } else if (parameters.length == 4 && (CommonConstant.DELETE).equals(parameters[3])) {
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

    if (page != 1 && page > Math.ceil(count / (float) pageSize)) {
      show404(request, response);
      return;
    }
    request.setAttribute(StudentConstant.STUDENT_LIST, studentList);
    request.setAttribute(PageConstant.RECORDS_PER_PAGE, pageSize);
    request.setAttribute(PageConstant.TOTAL_RECORDS, count);
    request.setAttribute(PageConstant.PAGE_NUMBER, page);
    request.getRequestDispatcher(StudentConstant.LIST_PAGE).forward(request, response);
  }

  private void show(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException, DataException {
    try {
      int id = parameterValueAsInt(request, 2);
      Student student = studentService.fetchStudentById(id);

      request.setAttribute(StudentConstant.STUDENT, student);
      request.getRequestDispatcher(StudentConstant.EDIT_PAGE).forward(request, response);
    } catch (NumberFormatException e) {
      show404(request, response);
    }
  }

  private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher(StudentConstant.CREATE_PAGE).forward(request, response);
  }

  private void createProcess(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException, DataException {
    try {
      Student student = constructStudentFromRequest(request);
      studentService.insert(student);

      response.sendRedirect(request.getContextPath() + StudentConstant.STUDENT_LIST_CONTROLLER);
    } catch (NumberFormatException e) {
      request.setAttribute(StudentConstant.ROLL, request.getParameter(StudentConstant.ROLL));
      request.setAttribute(StudentConstant.NAME, request.getParameter(StudentConstant.NAME));
      request.setAttribute(CommonConstant.MESSAGE, StudentConstant.INVALID_ROLL_MESSAGE);
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      request.getRequestDispatcher(StudentConstant.CREATE_PAGE).forward(request, response);
    }
  }

  private Student constructStudentFromRequest(HttpServletRequest request) {
    String roll = request.getParameter(StudentConstant.ROLL);
    String name = request.getParameter(StudentConstant.NAME);

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

      request.setAttribute(StudentConstant.STUDENT, student);
      request.getRequestDispatcher(StudentConstant.EDIT_PAGE).forward(request, response);
    } catch (NumberFormatException e) {
      show404(request, response);
    }
  }

  private void editProcess(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException, DataException {
    try {
      int id = parameterValueAsInt(request, 2);
      Student student = constructStudentFromRequest(request);
      studentService.edit(student, id);

      response.sendRedirect(request.getContextPath() + StudentConstant.STUDENT_LIST_CONTROLLER);
    } catch (NumberFormatException e) {
      request.setAttribute(StudentConstant.ROLL, request.getParameter(StudentConstant.ROLL));
      request.setAttribute(StudentConstant.NAME, request.getParameter(StudentConstant.NAME));
      request.setAttribute(CommonConstant.MESSAGE, StudentConstant.INVALID_ROLL_MESSAGE);
      request.getRequestDispatcher(StudentConstant.EDIT_PAGE).forward(request, response);
    }
  }

  private void deleteProcess(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException, DataException {
    int id = parameterValueAsInt(request, 2);
    studentService.delete(id);

    response.sendRedirect(request.getContextPath() + StudentConstant.STUDENT_LIST_CONTROLLER);
  }
}
