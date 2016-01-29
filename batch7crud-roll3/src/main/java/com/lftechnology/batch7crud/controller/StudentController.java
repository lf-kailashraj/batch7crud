package com.lftechnology.batch7crud.controller;

import static com.lftechnology.batch7crud.constant.CommonConstant.CREATE;
import static com.lftechnology.batch7crud.constant.CommonConstant.DELETE;
import static com.lftechnology.batch7crud.constant.CommonConstant.EDIT;
import static com.lftechnology.batch7crud.constant.CommonConstant.INTERNAL_SERVER_ERROR;
import static com.lftechnology.batch7crud.constant.CommonConstant.LIST;
import static com.lftechnology.batch7crud.constant.CommonConstant.MESSAGE;
import static com.lftechnology.batch7crud.constant.CommonConstant.NUMBER_OF_PAGES;
import static com.lftechnology.batch7crud.constant.CommonConstant.PAGE_NOT_FOUND;
import static com.lftechnology.batch7crud.constant.CommonConstant.PAGE_NUMBER;
import static com.lftechnology.batch7crud.constant.CommonConstant.RECORDS_PER_PAGE;
import static com.lftechnology.batch7crud.constant.CommonConstant.SHOW;
import static com.lftechnology.batch7crud.constant.StudentConstant.CREATE_PAGE;
import static com.lftechnology.batch7crud.constant.StudentConstant.EDIT_PAGE;
import static com.lftechnology.batch7crud.constant.StudentConstant.LIST_PAGE;
import static com.lftechnology.batch7crud.constant.StudentConstant.NAME;
import static com.lftechnology.batch7crud.constant.StudentConstant.ROLL;
import static com.lftechnology.batch7crud.constant.StudentConstant.SHOW_PAGE;
import static com.lftechnology.batch7crud.constant.StudentConstant.STUDENT;
import static com.lftechnology.batch7crud.constant.StudentConstant.STUDENT_LIST;
import static com.lftechnology.batch7crud.constant.StudentConstant.STUDENT_LIST_CONTROLLER;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.factory.StudentFactory;
import com.lftechnology.batch7crud.service.StudentService;
import com.lftechnology.batch7crud.validator.StudentValidator;

/**
 * This controller handles CRUD operations. It accepts URLs: students/, students
 * for listing, students/create for creating, students/id/edit for editing,
 * students/id/delete for deleting and students/id for showing records.
 * 
 * @author bishal
 *
 */
@WebServlet("/students/*")
public class StudentController extends CustomHttpServlet {
  private StudentService studentService = new StudentService();
  private StudentValidator studentValidator = new StudentValidator();
  private StudentFactory studentFactory = new StudentFactory();

  private static final Logger LOGGER = Logger.getLogger(StudentController.class.getName());

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    validateCrudURL(request);
    try {
      processRequestForGet(request, response);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);

      throw new ServletException(INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    validateCrudURL(request);
    try {
      processRequestForPost(request, response);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);

      throw new ServletException(INTERNAL_SERVER_ERROR);
    }
  }

  private void processRequestForGet(HttpServletRequest request, HttpServletResponse response)
      throws DataException, ServletException, IOException {
    String action = getAction(request);

    switch (action) {
    case LIST:
      list(request, response);
      break;
    case SHOW:
      show(request, response);
      break;
    case CREATE:
      create(request, response);
      break;
    case EDIT:
      edit(request, response);
      break;
    default:
      break;
    }
  }

  private void processRequestForPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException, DataException {
    String action = getAction(request);
    switch (action) {
    case CREATE:
      createProcess(request, response);
      break;

    case DELETE:
      deleteProcess(request, response);
      break;

    case EDIT:
      editProcess(request, response);
      break;

    default:
    }
  }

  private void list(HttpServletRequest request, HttpServletResponse response)
      throws DataException, ServletException, IOException {
    int page = 1;

    try {
      page = getPageNumber(request);
    } catch (NumberFormatException e) {
      throw new ServletException(PAGE_NOT_FOUND);
    }

    List<Student> studentList = studentService.fetch(page, RECORDS_PER_PAGE);

    int count = studentService.fetchTotalCount();
    int numberOfPages = (int) Math.ceil(count / (float) RECORDS_PER_PAGE);

    if (page != 1 && page > numberOfPages) {
      throw new ServletException(PAGE_NOT_FOUND);
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

      if (student == null) {
        throw new ServletException(PAGE_NOT_FOUND);
      }
      request.setAttribute(STUDENT, student);
      request.getRequestDispatcher(SHOW_PAGE).forward(request, response);
    } catch (NumberFormatException e) {
      throw new ServletException(PAGE_NOT_FOUND);
    }
  }

  private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher(CREATE_PAGE).forward(request, response);
  }

  private void createProcess(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException, DataException {
    try {
      Map<String, String> inputs = constructHashMapFromRequest(request);

      studentValidator.validateInputs(inputs);
      Student student = studentFactory.createObject(inputs);
      studentService.insert(student);

      response.sendRedirect(request.getContextPath() + STUDENT_LIST_CONTROLLER);
    } catch (ValidationException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);

      request.setAttribute(MESSAGE, e.getErrors());
      request.getRequestDispatcher(CREATE_PAGE).forward(request, response);
    }
  }

  private Map<String, String> constructHashMapFromRequest(HttpServletRequest request) {
    Map inputs = new HashMap<String, String>();
    String roll = request.getParameter(ROLL);
    String name = request.getParameter(NAME);

    inputs.put(ROLL, roll);
    inputs.put(NAME, name);

    return inputs;
  }

  private void edit(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException, DataException {
    try {
      int id = parameterValueAsInt(request, 2);
      Student student = studentService.fetchStudentById(id);

      if (student == null) {
        throw new ServletException(PAGE_NOT_FOUND);
      }
      request.setAttribute(STUDENT, student);
      request.getRequestDispatcher(EDIT_PAGE).forward(request, response);
    } catch (NumberFormatException e) {
      throw new ServletException(PAGE_NOT_FOUND);
    }
  }

  private void editProcess(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException, DataException {
    Student student = new Student();
    try {
      int id = parameterValueAsInt(request, 2);

      Map<String, String> inputs = constructHashMapFromRequest(request);

      studentValidator.validateInputs(inputs);

      student = studentFactory.createObject(inputs);
      student.setId(id);

      studentService.edit(student);

      response.sendRedirect(request.getContextPath() + STUDENT_LIST_CONTROLLER);
    } catch (ValidationException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);

      request.setAttribute(STUDENT, student);
      request.setAttribute(MESSAGE, e.getErrors());
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
