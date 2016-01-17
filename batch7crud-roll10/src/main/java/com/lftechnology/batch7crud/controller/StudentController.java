package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.entity.Person;
import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.service.StudentService;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getPathInfo();

        if(servletPath == null || "/".equalsIgnoreCase(servletPath)){
            List<Student> studentList = studentService.fetchData();
            req.setAttribute("studentList", studentList);
            req.getServletContext().getRequestDispatcher("/WEB-INF/views/students.jsp").forward(req, resp);
        }else if("/add".equalsIgnoreCase(servletPath)){
            req.getServletContext().getRequestDispatcher("/WEB-INF/views/addStudent.jsp").forward(req, resp);
        }else if("/edit".equalsIgnoreCase(servletPath)){
            req.getServletContext().getRequestDispatcher("/WEB-INF/views/editStudent.jsp").forward(req, resp);
        }else if("/delete".equalsIgnoreCase(servletPath)){
            req.getServletContext().getRequestDispatcher("/WEB-INF/views/students.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getPathInfo();
        System.out.println("servlet path " + servletPath);

        if("/add".equalsIgnoreCase(servletPath)){
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

            Person person = new Person();
            person.setName(name);
            person.setAddress(address);
            person.setDob(date);

            Integer roll = Integer.parseInt(req.getParameter("roll"));
            String department = req.getParameter("department");
            String batch = req.getParameter("batch");

            Student student = new Student();
            student.setPerson(person);
            student.setBatch(batch);
            student.setDepartment(department);
            student.setRoll(roll);

            studentService.add(student);


            resp.sendRedirect("/students");
        }else if("/edit".equalsIgnoreCase(servletPath)){
            //handle edit operation
            req.getServletContext().getRequestDispatcher("/WEB-INF/views/editStudent.jsp").forward(req, resp);
        }else if("/delete".equalsIgnoreCase(servletPath)){
            //handle delete operation
            req.getServletContext().getRequestDispatcher("/WEB-INF/views/students.jsp").forward(req, resp);
        }

    }
}
