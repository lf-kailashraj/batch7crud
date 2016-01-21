package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Student;
import com.lftechnology.batch7crud.service.StudentServices;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by leapfrog on 1/18/16.
 */
@WebServlet(name = "StudentListController", urlPatterns = { "/students/*" })
public class StudentListController extends CommonHttpServlet{
  private static final Logger LOGGER = Logger.getLogger(StudentListController.class.getName());

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
        showErrorPage(request, response);
    } catch (DataException | ServletException | IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String[] urlPath = urlParts(request);
    try {
      if (urlPath.length == 2)
        list(request, response);
      else if (urlPath.length == 3 && CommonConstants.NEW_ENTRY.equals(urlPath[2]))
        create(request, response);
      else if (urlPath.length == 4 && CommonConstants.EDIT.equals(urlPath[3]))
        edit(request, response, Integer.parseInt(urlPath[2]));
      else if (urlPath.length == 3)
        show(request, response);
      else{
        showErrorPage(request, response);
      }
    } catch (DataException | ServletException | IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DataException {
    StudentServices studentService = new StudentServices();
    int pageSize = CommonConstants.PAGE_SIZE;
    int page = 1;
    try {
      page = getPageNumber(request);
    } catch (NumberFormatException e) {
      showErrorPage(request, response);
    }
    int totalStudents = studentService.fetchTotal();
    int numberOfPages = (int) Math.ceil(totalStudents / (float) pageSize);

    if (page != 1 && page > numberOfPages ) {
      showErrorPage(request, response);
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
    String name = request.getParameter("name");
    String address = request.getParameter("address");
    String roll = request.getParameter("roll");
    try {

      int rollNum = Integer.parseInt(roll);
      Student student = new Student();
      student.setName(name);
      student.setAddress(address);
      student.setRoll(rollNum);

      StudentServices studentService = new StudentServices();
      studentService.addNew(student);
      response.sendRedirect(request.getContextPath() + "/" + CommonConstants.LIST_URL);
    } catch (NumberFormatException e) {
      request.setAttribute("error", "invalid roll");
      request.getServletContext().getRequestDispatcher(CommonConstants.NEW_ENTRY_VIEW).forward(request, response);
    }

  }

  private void edit(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException, DataException {
    StudentServices studentService = new StudentServices();

    request.setAttribute("student", studentService.fetchById(id));
    request.getRequestDispatcher(CommonConstants.EDIT_ENTRY_VIEW).forward(request, response);

  }

  private void editProcess(HttpServletRequest request, HttpServletResponse response, int id)
    throws ServletException, IOException, DataException {
    String name = request.getParameter("name");
    String address = request.getParameter("address");
    int roll = Integer.parseInt(request.getParameter("roll"));
    Student student = new Student();
    student.setName(name);
    student.setAddress(address);
    student.setRoll(roll);
    student.setId(id);

    StudentServices studentService = new StudentServices();
    studentService.update(student);
    response.sendRedirect(request.getContextPath() + File.separator + CommonConstants.LIST_URL);

  }

  private void deleteProcess(HttpServletRequest request, HttpServletResponse response, int id)
    throws ServletException, IOException, DataException {
    StudentServices studentService = new StudentServices();
    studentService.delete(id);
    response.sendRedirect(request.getContextPath() + File.separator + CommonConstants.LIST_URL);

  }

  private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DataException {
    try {
      int id = urlInteger(request, 2);
      StudentServices studentService = new StudentServices();
      Student student = studentService.fetchById(id);
      if (student == null)
        showErrorPage(request, response);
      else {
        request.setAttribute("student", student);
        request.getRequestDispatcher(CommonConstants.SHOW_STUDENT_VIEW).forward(request, response);
      }
    } catch (NumberFormatException e) {
      showErrorPage(request, response);
    }
  }

}
