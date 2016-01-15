package com.lftechnology.batch7crud.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.*;


/**
 * Created by sanjay on 1/13/16.
 */
@WebServlet("/DisplayServlet")
public class DisplayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String address= request.getParameter("address");
        int age = Integer.parseInt(request.getParameter("age"));
        request.setAttribute("name",name);
        request.setAttribute("address",address);
        request.setAttribute("age", age);
        request.getServletContext().getRequestDispatcher("/WEB-INF/views/display.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/WEB-INF/views/display.jsp").forward(request, response);
    }
}
