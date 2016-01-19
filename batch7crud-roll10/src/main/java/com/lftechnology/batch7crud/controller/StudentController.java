package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.service.StudentService;
import com.lftechnology.batch7crud.util.TypeCaster;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Math.ceil;

/**
 * @Author binodnme
 * Created on 1/14/16
 */
@WebServlet("/students/*")
public class StudentController extends CustomHttpServlet {
    private static final Logger LOGGER = Logger.getLogger(StudentController.class.getName());
    private static StudentService studentService = new StudentService();
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final Integer TOTAL_DATA_TO_FETCH = 2;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] params = params(req);

        if (params.length<=1) {
            Integer page = 1;
            try {
                String pageText = req.getParameter("page");
                if(pageText!=null){
                    page = Integer.parseInt(pageText);
                }
            } catch (NumberFormatException e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
            }

            list(req, resp, page);

        } else {

            if ("create".equalsIgnoreCase(params[1])) {
                create(req, resp);

            } else if ("edit".equalsIgnoreCase(params[2])) {
                Integer studentId = TypeCaster.toInt(params[1]);
                if (studentId == null) {
                    showPageNotFound(req, resp);
                } else {
                    edit(req, resp, studentId);
                }

            }
        }

    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] params = params(req);

        if ("create".equalsIgnoreCase(params[1])) {
            createProcess(req, resp);

        } else if ("edit".equalsIgnoreCase(params[2])) {
            Integer studentId = TypeCaster.toInt(params[1]);
            if (studentId == null) {
                showPageNotFound(req, resp);
            } else {
                editProcess(req, resp, studentId);
            }

        } else if ("delete".equalsIgnoreCase(params[2])) {
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
            Integer offset = (page - 1)*TOTAL_DATA_TO_FETCH;
            List<Student> studentList = studentService.fetch(offset, TOTAL_DATA_TO_FETCH);
            Integer totalRecord = studentService.fetchTotalRecordNumber();
            Double totalPages = ceil(totalRecord*1.0 / TOTAL_DATA_TO_FETCH);

            req.setAttribute("studentList", studentList);
            req.setAttribute("totalPages", totalPages.intValue());
            req.setAttribute("currentPage", page);
            req.getServletContext().getRequestDispatcher("/WEB-INF/views/students.jsp").forward(req, resp);
        } catch (DataException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            showServerError(req, resp, e);
        }
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/WEB-INF/views/createStudent.jsp").forward(req, resp);
    }

    private void createProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String dob = req.getParameter("dob");
        String department = req.getParameter("department");
        String batch = req.getParameter("batch");
        String rollText = req.getParameter("roll");

        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
            Date date = dateFormat.parse(dob);
            Integer roll = Integer.parseInt(rollText);

            Student student = new Student();
            student.setBatch(batch);
            student.setDepartment(department);
            student.setRoll(roll);

            student.setName(name);
            student.setAddress(address);
            student.setDob(date);

            studentService.insert(student);
            resp.sendRedirect("/students");

        } catch (ParseException e) {
            req.setAttribute(ERROR_MESSAGE, "error while parsing date");
            create(req, resp);
        } catch (NumberFormatException e) {
            req.setAttribute(ERROR_MESSAGE, "number format error in 'roll'");
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
            req.getServletContext().getRequestDispatcher("/WEB-INF/views/editStudent.jsp").forward(req, resp);
        } catch (DataException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            showServerError(req, resp, e);
        }
    }

    private void editProcess(HttpServletRequest req, HttpServletResponse resp, Integer id) throws ServletException, IOException {
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String dob = req.getParameter("dob");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
        Date date = null;
        try {
            date = dateFormat.parse(dob);
        } catch (ParseException e) {
            LOGGER.severe(e.getMessage());
        }

        Integer roll = Integer.parseInt(req.getParameter("roll"));
        String department = req.getParameter("department");
        String batch = req.getParameter("batch");

        Student student = new Student();
        student.setId(id);
        student.setBatch(batch);
        student.setDepartment(department);
        student.setRoll(roll);

        student.setName(name);
        student.setAddress(address);
        student.setDob(date);

        try {
            studentService.update(student);
            resp.sendRedirect("/students");
        } catch (DataException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            showServerError(req, resp, e);
        }
    }

    private void deleteProcess(HttpServletRequest req, HttpServletResponse resp, int id) throws ServletException, IOException {
        try {
            studentService.delete(id);
            resp.sendRedirect("/students");
        } catch (DataException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            showServerError(req, resp, e);
        }
    }
}
