package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constant.EntityConstant;
import com.lftechnology.batch7crud.constant.PageConstant;
import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.factory.StudentFactory;
import com.lftechnology.batch7crud.service.StudentService;
import com.lftechnology.batch7crud.util.TypeCaster;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private static final String ERRORS = "errors";
    private static final String STUDENT = "student";

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
            if (PageConstant.CREATE.equalsIgnoreCase(params[1])) {
                create(req, resp);
            } else if (PageConstant.EDIT.equalsIgnoreCase(params[2])) {
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

        if (PageConstant.CREATE.equalsIgnoreCase(params[1])) {
            createProcess(req, resp);
        } else if (PageConstant.EDIT.equalsIgnoreCase(params[2])) {
            Integer studentId = TypeCaster.toInt(params[1]);
            if (studentId == null) {
                showPageNotFound(req, resp);
            } else {
                editProcess(req, resp, studentId);
            }
        } else if (PageConstant.DELETE.equalsIgnoreCase(params[2])) {
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
            Integer offset = (page - 1) * PageConstant.TOTAL_DATA_TO_FETCH;
            List<Student> studentList = studentService.fetch(offset, PageConstant.TOTAL_DATA_TO_FETCH);
            Integer totalRecord = studentService.fetchTotalRecordNumber();
            Double totalPages = ceil(totalRecord * 1.0 / PageConstant.TOTAL_DATA_TO_FETCH);

            req.setAttribute(STUDENT_LIST, studentList);
            req.setAttribute(PageConstant.TOTAL_PAGES, totalPages.intValue());
            req.setAttribute(PageConstant.CURRENT_PAGE, page);
            req.getServletContext().getRequestDispatcher(PageConstant.STUDENT_LIST_VIEW).forward(req, resp);
        } catch (DataException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            showServerError(req, resp, e);
        }
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher(PageConstant.STUDENT_CREATE_VIEW).forward(req, resp);
    }

    private void createProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Map<String, String> errors = new HashMap<>();
            Map<String, String> paramMap = buildParamMap(req);
            Student student = StudentFactory.createStudent(paramMap, errors);

            if (errors.isEmpty()) {
                studentService.insert(student);
                resp.sendRedirect(req.getContextPath() + PageConstant.STUDENT_LIST_URL);
            } else {
                req.setAttribute(ERRORS, errors);
                create(req, resp);
            }

        } catch (DataException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            showServerError(req, resp, e);

        } catch (ValidationException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            req.setAttribute(ERRORS, e.getErrors());
            create(req, resp);

        }
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp, Integer id) throws ServletException, IOException {
        try {
            Student student = studentService.fetchById(id);
            req.setAttribute(STUDENT, student);
            req.getServletContext().getRequestDispatcher(PageConstant.STUDENT_EDIT_VIEW).forward(req, resp);
        } catch (DataException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            showServerError(req, resp, e);
        }
    }

    private void editProcess(HttpServletRequest req, HttpServletResponse resp, Integer id) throws ServletException, IOException {
        try {

            Map<String, String> errors = new HashMap<>();
            Map<String, String> paramMap = buildParamMap(req);

            Student student = StudentFactory.createStudent(paramMap, errors);
            if (errors.isEmpty()) {
                student.setId(id);
                studentService.update(student);
                resp.sendRedirect(req.getContextPath() + PageConstant.STUDENT_LIST_URL);
            } else {
                req.setAttribute(ERRORS, errors);
                edit(req, resp, id);
            }

        } catch (DataException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            showServerError(req, resp, e);

        } catch (ValidationException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            req.setAttribute(ERRORS, e.getErrors());
            edit(req, resp, id);

        }
    }

    private void deleteProcess(HttpServletRequest req, HttpServletResponse resp, int id) throws ServletException, IOException {
        try {
            studentService.delete(id);
            resp.sendRedirect(req.getContextPath() + PageConstant.STUDENT_LIST_URL);
        } catch (DataException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            showServerError(req, resp, e);
        }
    }

    private Map<String, String> buildParamMap(HttpServletRequest req) {
        String name = req.getParameter(EntityConstant.NAME);
        String address = req.getParameter(EntityConstant.ADDRESS);
        String dob = req.getParameter(EntityConstant.DOB);
        String department = req.getParameter(EntityConstant.DEPARTMENT);
        String batch = req.getParameter(EntityConstant.BATCH);
        String rollText = req.getParameter(EntityConstant.ROLL);

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put(EntityConstant.NAME, name);
        paramMap.put(EntityConstant.ADDRESS, address);
        paramMap.put(EntityConstant.DOB, dob);
        paramMap.put(EntityConstant.DEPARTMENT, department);
        paramMap.put(EntityConstant.BATCH, batch);
        paramMap.put(EntityConstant.ROLL, rollText);

        return paramMap;
    }
}
