package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constant.EntityConstant;
import com.lftechnology.batch7crud.constant.PageConstant;
import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.factory.StudentFactory;
import com.lftechnology.batch7crud.service.StudentService;
import com.lftechnology.batch7crud.util.TypeCaster;
import com.lftechnology.batch7crud.utils.requestmapper.RequestMapping;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
public class StudentController extends HttpServlet {
  private static StudentService studentService = new StudentService();
  private static final Logger LOGGER = Logger.getLogger(StudentController.class.getName());
  private static final String STUDENT_LIST = "studentList";
  private static final String ERRORS = "errors";
  private static final String STUDENT = "student";


  @RequestMapping(value = "list", method = "GET")
  private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { // NOSONAR
    try {
      Integer page = 1;
      String pageText = req.getParameter("page");
      if (pageText != null) {
        page = getPageNum(pageText);
      }

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
      throw new ServletException(PageConstant.INTERNAL_SERVER_ERROR);
    }
  }

  @RequestMapping(value = "create", method = "GET")
  private void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getServletContext().getRequestDispatcher(PageConstant.STUDENT_CREATE_VIEW).forward(req, resp);
  }

  @RequestMapping(value = "create", method = "POST")
  private void createProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { // NOSONAR
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
      throw new ServletException(PageConstant.INTERNAL_SERVER_ERROR);

    } catch (ValidationException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      req.setAttribute(ERRORS, e.getErrors());
      create(req, resp);

    }
  }

  @RequestMapping(value = "{id}/edit", method = "GET")
  private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {

      if(req.getAttribute(STUDENT)==null){
        Integer id = TypeCaster.toInt(req.getPathInfo().split("/")[1]);
        Student student = studentService.fetchById(id);
        if(student != null){
          req.setAttribute(STUDENT, student);
        }else{
          throw new ServletException(PageConstant.PAGE_NOT_FOUND);
        }

      }

      req.getServletContext().getRequestDispatcher(PageConstant.STUDENT_EDIT_VIEW).forward(req, resp);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new ServletException(PageConstant.INTERNAL_SERVER_ERROR);
    }
  }

  @RequestMapping(value = "{id}/delete", method = "POST")
  private void deleteProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { // NOSONAR
    try {
      Integer id = TypeCaster.toInt(req.getPathInfo().split("/")[1]);
      studentService.delete(id);
      resp.sendRedirect(req.getContextPath() + PageConstant.STUDENT_LIST_URL);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new ServletException(PageConstant.INTERNAL_SERVER_ERROR);
    }
  }

  @RequestMapping(value = "{id}/edit", method = "POST")
  private void editProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { // NOSONAR
    Map<String, String> errors = new HashMap<>();
    Map<String, String> paramMap = buildParamMap(req);
    Integer id = TypeCaster.toInt(req.getPathInfo().split("/")[1]);
    try {

      Student student = StudentFactory.createStudent(paramMap, errors);
      if (errors.isEmpty()) {
        student.setId(id);
        studentService.update(student);
        resp.sendRedirect(req.getContextPath() + PageConstant.STUDENT_LIST_URL);
      } else {
        paramMap.put(EntityConstant.ID, id.toString());
        req.setAttribute(STUDENT, paramMap);
        req.setAttribute(ERRORS, errors);
        edit(req, resp);
      }

    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);

      throw new ServletException(PageConstant.INTERNAL_SERVER_ERROR);

    } catch (ValidationException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      paramMap.put(EntityConstant.ID, id.toString());
      req.setAttribute(STUDENT, paramMap);
      req.setAttribute(ERRORS, e.getErrors());
      edit(req, resp);
    }
  }


  @RequestMapping(value = "createTest", method = "GET")
  private void createTest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getServletContext().getRequestDispatcher(PageConstant.STUDENT_CREATE_VIEW1).forward(req, resp);
  }

  @RequestMapping(value = "createTest", method = "POST")
  private void createTestProcess(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));

    String json = "";
    if(br != null){
      json = br.readLine();
    }


    try {
      JSONObject jsonObject = (JSONObject) new JSONParser().parse(json);
      Map<String, String> errors = new HashMap<>();
      Student student = StudentFactory.createStudent(jsonObject, errors);

      if (errors.isEmpty()) {
        studentService.insert(student);
        resp.setContentType("application/json");

        PrintWriter out = resp.getWriter();
        out.print(jsonObject);
        out.flush();
      } else {

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("errors", errors);
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        out.print(jsonObject1);
        out.flush();
      }

    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new ServletException(PageConstant.INTERNAL_SERVER_ERROR);

    } catch (ValidationException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);

      JSONObject jo = new JSONObject();
      jo.put("errors", e.getErrors());
      resp.setContentType("application/json");
      PrintWriter out = resp.getWriter();

      out.print(jo);
      out.flush();

    } catch (ParseException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
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

  private Integer getPageNum(String numText){
    try{
      return Integer.parseInt(numText);
    }catch (NumberFormatException e){
      LOGGER.log(Level.INFO, e.getMessage(), e);
      return 1;
    }
  }
}
