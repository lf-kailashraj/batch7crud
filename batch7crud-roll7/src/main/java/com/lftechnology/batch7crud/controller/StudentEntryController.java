package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Student;
import com.lftechnology.batch7crud.service.StudentServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by leapfrog on 1/18/16.
 */
@WebServlet(name = "StudentEntryController", urlPatterns = {"/Students/NewEntry"})
public class StudentEntryController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        int roll = Integer.parseInt(request.getParameter("roll"));

        System.out.println(name);
        System.out.println(address);
        Student s = new Student();
        s.setName(name);
        s.setAddress(address);
        s.setRoll(roll);

        StudentServices stdServices = new StudentServices();
        try {
            stdServices.addNew(s);
            response.sendRedirect("/Students/ViewAll");
        } catch (DataException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/WEB-INF/views/newEntry.jsp").forward(request,response);
    }
}
