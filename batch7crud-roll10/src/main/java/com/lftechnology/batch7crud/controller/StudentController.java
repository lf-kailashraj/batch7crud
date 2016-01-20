package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constants.Constant;
import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.service.StudentService;
import com.lftechnology.batch7crud.util.TypeCaster;
import com.lftechnology.batch7crud.utils.DateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Math.ceil;

/**
 * This class handles all the request about the students, performs the necessary operations and
 * sends the response to the proper view page.
 *
 * @Author Binod Shrestha <binodshrestha@lftechnology.com>
 * Created on 1/14/16
 */
@WebServlet("/students/*")
public class StudentController extends CustomHttpServlet {
  private static StudentService studentService = new StudentService();
  private static final Logger LOGGER = Logger.getLogger(StudentController.class.getName());
  private static final String STUDENT_LIST = "studentList";

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String[] params = params(req);

    if (params.length <= 1) {
      Integer page = 1;
      try {
        String pageText = req.getParameter("page");
        if (pageText != null) {
          page = Integer.parseInt(pageText);
        }
      } catch (NumberFormatException e) {
        LOGGER.log(Level.SEVERE, e.getMessage(), e);
      }
      list(req, resp, page);

    } else {
      if (Constant.CREATE.equalsIgnoreCase(params[1])) {
        create(req, resp);
      } else if (Constant.EDIT.equalsIgnoreCase(params[2])) {
        Integer studentId = TypeCaster.toInt(params[1]);
        if (studentId == null) {
          showPageNotFound(req, resp);
        } else {
          edit(req, resp, studentId);
        }
      }
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String[] params = params(req);

    if (Constant.CREATE.equalsIgnoreCase(params[1])) {
      createProcess(req, resp);
    } else if (Constant.EDIT.equalsIgnoreCase(params[2])) {
      Integer studentId = TypeCaster.toInt(params[1]);
      if (studentId == null) {
        showPageNotFound(req, resp);
      } else {
        editProcess(req, resp, studentId);
      }
    } else if (Constant.DELETE.equalsIgnoreCase(params[2])) {
      Integer studentId = TypeCaster.toInt(params[1]);
      if (studentId == null) {
        showPageNotFound(req, resp);
      } else {
        deleteProcess(req, resp, studentId);
      }
    }
  }

  private void list(HttpServletRequest req, HttpServletResponse resp, int page) throws ServletException, IOException {
    try {
      Integer offset = (page - 1) * Constant.TOTAL_DATA_TO_FETCH;
      List<Student> studentList = studentService.fetch(offset, Constant.TOTAL_DATA_TO_FETCH);
      Integer totalRecord = studentService.fetchTotalRecordNumber();
      Double totalPages = ceil(totalRecord * 1.0 / Constant.TOTAL_DATA_TO_FETCH);

      req.setAttribute(STUDENT_LIST, studentList);
      req.setAttribute(Constant.TOTAL_PAGES, totalPages.intValue());
      req.setAttribute(Constant.CURRENT_PAGE, page);
      req.getServletContext().getRequestDispatcher(Constant.STUDENT_LIST_VIEW).forward(req, resp);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      showServerError(req, resp, e);
    }
  }

  private void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getServletContext().getRequestDispatcher(Constant.STUDENT_CREATE_VIEW).forward(req, resp);
  }

  private void createProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      Student student = constructStudentFromRequest(req);
      studentService.insert(student);
      resp.sendRedirect(Constant.STUDENT_LIST_URL);
    } catch (ParseException e) {
      req.setAttribute(Constant.ERROR_MESSAGE, "error while parsing date");
      create(req, resp);
    } catch (NumberFormatException e) {
      req.setAttribute(Constant.ERROR_MESSAGE, "number format error in 'roll'");
      create(req, resp);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      showServerError(req, resp, e);
    }
  }

  private void edit(HttpServletRequest req, HttpServletResponse resp, Integer id) throws ServletException, IOException {
    try {
      Student student = studentService.fetchById(id);
      req.setAttribute("student", student);
      req.getServletContext().getRequestDispatcher(Constant.STUDENT_EDIT_VIEW).forward(req, resp);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      showServerError(req, resp, e);
    }
  }

  private void editProcess(HttpServletRequest req, HttpServletResponse resp, Integer id) throws ServletException, IOException {
    try {
      Student student = constructStudentFromRequest(req);
      student.setId(id);
      studentService.update(student);
      resp.sendRedirect(Constant.STUDENT_LIST_URL);
    } catch (ParseException e) {
      req.setAttribute(Constant.ERROR_MESSAGE, "error while parsing date");
      edit(req, resp, id);
    } catch (NumberFormatException e) {
      req.setAttribute(Constant.ERROR_MESSAGE, "number format error in 'roll'");
      edit(req, resp, id);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      showServerError(req, resp, e);
    }
  }

  private void deleteProcess(HttpServletRequest req, HttpServletResponse resp, int id) throws ServletException, IOException {
    try {
      studentService.delete(id);
      resp.sendRedirect(Constant.STUDENT_LIST_URL);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      showServerError(req, resp, e);
    }
  }

  private Student constructStudentFromRequest(HttpServletRequest req) throws ParseException {
    String name = req.getParameter(Constant.NAME);
    String address = req.getParameter(Constant.ADDRESS);
    String dob = req.getParameter(Constant.DOB);
    String department = req.getParameter(Constant.DEPARTMENT);
    String batch = req.getParameter(Constant.BATCH);
    String rollText = req.getParameter(Constant.ROLL);

    Date date = DateUtils.parse(dob);
    Integer roll = Integer.parseInt(rollText);

    Student student = new Student();
    student.setBatch(batch);
    student.setDepartment(department);
    student.setRoll(roll);
    student.setName(name);
    student.setAddress(address);
    student.setDob(date);

    return student;
  }
}
