package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.service.StudentService;
import com.lftechnology.batch7crud.util.TypeCaster;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @Author binodnme
 * Created on 1/14/16
 */
@WebServlet("/students/*")
public class StudentController extends HttpServlet{
    StudentService studentService = new StudentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String servletPath = req.getPathInfo();
        System.out.println("servlet path: "+servletPath);
        if(servletPath == null || "/".equalsIgnoreCase(servletPath)){
            Integer page = 1;
            try{
                page = Integer.parseInt(req.getParameter("page"));
            }catch (NumberFormatException e){}

            list(req, resp, page);

        }else{
            String[] params = servletPath.split("/");

            if(params[1].equalsIgnoreCase("create")){
                create(req, resp);

            } else if(params[2].equalsIgnoreCase("edit")){
                Integer studentId = TypeCaster.toInt(params[1]);
                edit(req, resp,studentId);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getPathInfo();
        System.out.println("servlet path " + servletPath);

        String[] params = servletPath.split("/");

        if(params[1].equalsIgnoreCase("create")){
            createProcess(req,resp);

        }else if(params[2].equalsIgnoreCase("edit")){
            Integer studentId = TypeCaster.toInt(params[1]);
            editProcess(req, resp, studentId);
        }else if(params[2].equalsIgnoreCase("delete")){
            Integer studentId = TypeCaster.toInt(params[1]);
            deleteProcess(req, resp, studentId);
        }
    }


    private void list(HttpServletRequest req, HttpServletResponse resp, int page) throws ServletException, IOException {
        try {
            List<Student> studentList = studentService.fetch(page);
            req.setAttribute("studentList", studentList);
            req.setAttribute("nextPageNum",page+1);
            req.getServletContext().getRequestDispatcher("/WEB-INF/views/students.jsp").forward(req, resp);
        } catch (DataException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            req.getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
    }


    private void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/WEB-INF/views/createStudent.jsp").forward(req, resp);
    }


    private void createProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name= req.getParameter("name");
        String address = req.getParameter("address");
        String dob = req.getParameter("dob");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
        Date date=null;
        try {
            date = dateFormat.parse(dob);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Integer roll = Integer.parseInt(req.getParameter("roll"));
        String department = req.getParameter("department");
        String batch = req.getParameter("batch");

        Student student = new Student();
        student.setBatch(batch);
        student.setDepartment(department);
        student.setRoll(roll);

        student.setName(name);
        student.setAddress(address);
        student.setDob(date);

        try {
            studentService.insert(student);
            resp.sendRedirect("/students");
        } catch (DataException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            req.getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
    }


    private void edit(HttpServletRequest req, HttpServletResponse resp, Integer id) throws ServletException, IOException {
        Student student = null;
        try {
            student = studentService.getStudentById(id);
            req.setAttribute("student",student);
            req.getServletContext().getRequestDispatcher("/WEB-INF/views/editStudent.jsp").forward(req, resp);
        } catch (DataException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            req.getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
    }


    private void editProcess(HttpServletRequest req, HttpServletResponse resp, Integer id) throws ServletException, IOException {
        String name= req.getParameter("name");
        String address = req.getParameter("address");
        String dob = req.getParameter("dob");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
        Date date=null;
        try {
            date = dateFormat.parse(dob);
        } catch (ParseException e) {
            e.printStackTrace();
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
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            req.getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
    }


    private void deleteProcess(HttpServletRequest req, HttpServletResponse resp, int id) throws ServletException, IOException {
        try {
            studentService.delete(id);
            resp.sendRedirect("/students");
        } catch (DataException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
