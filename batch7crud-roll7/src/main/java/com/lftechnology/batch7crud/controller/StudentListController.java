package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.exception.DataException;
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
@WebServlet(name = "StudentListController", urlPatterns = {"/Students/*"})
public class StudentListController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StudentServices stdservice = new StudentServices();
        try {
            request.setAttribute("students",stdservice.fetch(1));
            request.getServletContext().getRequestDispatcher("/WEB-INF/views/students.jsp").forward(request,response);
        } catch (DataException e) {
            e.printStackTrace();
        }
    }
}
