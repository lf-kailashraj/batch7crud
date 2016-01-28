package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constants.*;
import com.lftechnology.batch7crud.model.Student;
import com.lftechnology.batch7crud.service.StudentService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.List;
import java.util.logging.Level;
import java.io.File;

/**
 * Created by sagarmatha on 1/27/16.
 */

@WebServlet("/students/*")
public class StudentController extends CommonHTTPRequestHAndler {
    private static final Logger LOGGER = Logger.getLogger(StudentController.class.getName());
    private static StudentService studentService = new StudentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String urlPath = req.getRequestURI().substring(req.getContextPath().length());
        String[] parameters = urlPath.split(File.separator);
        try {
            if (parameters.length == 2 && UrlConstants.STUDENTS.equals(parameters[1])) {
                listStudents(req, res);
            } else if (parameters.length == 3 && UrlConstants.CREATE.equals(parameters[2])) {
                createStudent(req, res);
            } else if (parameters.length == 4 && UrlConstants.UPDATE.equals(parameters[3])) {
                updateStudent(req, res);
            } else if (parameters.length == 4 && UrlConstants.VIEW.equals(parameters[3])) {
                viewStudentDetail(req, res);
            } else {
                show404(req,res);
            }
        } catch (ServletException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            show500(req, res);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            show500(req, res);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String urlPath = req.getRequestURI().substring(req.getContextPath().length());
        String[] parameters = urlPath.split(File.separator);
        try {
            if (parameters.length == 3 && UrlConstants.CREATE.equals(parameters[2])) {
                createMethod(req, res);
            } else if (parameters.length == 4 && UrlConstants.UPDATE.equals(parameters[3])) {
                updateMethod(req, res);
            } else if (parameters.length == 4 && UrlConstants.DELETE.equals(parameters[3])) {
                deleteMethod(req, res);
            } else {
                show404(req,res);
            }
        } catch (ServletException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            show500(req, res);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            show500(req, res);
        }
    }

    private void createStudent(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher(PageConstants.NEW_STUDENT).forward(req, res);
    }

    private void listStudents(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<Student> stdList;
        try {
            int page = pageNumber(req);
            stdList = studentService.viewList(page, NumberConstants.LIMIT);
            int totalCount = studentService.countStudents();
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
            int studentId= Integer.parseInt(urlPath.split(File.separator)[2]);
            Student std = studentService.viewDetail(studentId);
            int totalStudents = studentService.countStudents();
            req.setAttribute(AttributeConstants.STUDENT, std);
            req.setAttribute(AttributeConstants.CURRENT_STUDENT, studentId);
            req.setAttribute(AttributeConstants.TOTAL_STUDENTS, totalStudents);
            req.getServletContext().getRequestDispatcher(PageConstants.DETAIL_VIEW).forward(req, res);
        }catch (ServletException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            show404(req,res);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            show404(req, res);
        }
    }

    private void updateStudent(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            int studentId = parameterValueAsInt(req, 2);
            req.setAttribute(AttributeConstants.STUDENT, studentService.viewDetail(studentId));
            RequestDispatcher view = req.getRequestDispatcher(PageConstants.UPDATE);
            view.forward(req, res);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            show404(req,res);
        }
    }

    private void createMethod(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Student student = new Student();
        student.setFirstName(req.getParameter(ParameterConstants.FIRST_NAME));
        student.setLastName(req.getParameter(ParameterConstants.LAST_NAME));
        student.setAge(Integer.parseInt(req.getParameter(ParameterConstants.AGE)));
        student.setAddress(req.getParameter(ParameterConstants.ADDRESS));
        try {
            studentService.insert(student);
            res.sendRedirect(req.getContextPath());
        } catch (NumberFormatException e) {
            show404(req, res);
        }
    }

    private void updateMethod(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            String urlPath = req.getRequestURI().substring(req.getContextPath().length());
            int studentId= Integer.parseInt(urlPath.split(File.separator)[2]);
            Student student = new Student();
            student.setStudentID(studentId);
            student.setFirstName(req.getParameter(ParameterConstants.FIRST_NAME));
            student.setLastName(req.getParameter(ParameterConstants.LAST_NAME));
            student.setAge(Integer.parseInt(req.getParameter(ParameterConstants.AGE)));
            student.setAddress(req.getParameter(ParameterConstants.ADDRESS));
            studentService.update(student);
            res.sendRedirect(req.getContextPath() + File.separator + UrlConstants.STUDENTS);
        } catch (NumberFormatException e) {
            show404(req, res);
        }
    }

    private void deleteMethod(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            String urlPath = req.getRequestURI().substring(req.getContextPath().length());
            int studentId= Integer.parseInt(urlPath.split(File.separator)[2]);
            studentService.delete(studentId);
            res.sendRedirect(req.getContextPath() + File.separator + UrlConstants.STUDENTS);
        } catch (NumberFormatException e) {
            show404(req, res);
        }
    }
}
