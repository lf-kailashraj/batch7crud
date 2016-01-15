package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.entity.Grade;
import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.services.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sanjay on 1/14/16.
 */
@WebServlet(name = "NewStudent",urlPatterns = {"/NewStudent"})
public class NewStudent extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String middleName = request.getParameter("mname");
        String address = request.getParameter("address");
        int grade = Integer.parseInt(request.getParameter("grade"));
        Student s = new Student();
        s.setFirstName(firstName);
        s.setMiddleName(middleName);
        s.setLastName(lastName);
        s.setAddress(address);
        s.setGrade(grade);

        StudentService stdService = new StudentService();
        stdService.save(s);
        response.sendRedirect("/DisplayServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/WEB-INF/views/newStudent.jsp").forward(request, response);
    }
}
