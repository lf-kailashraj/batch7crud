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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by leapfrog on 1/18/16.
 */
@WebServlet(name = "StudentListController", urlPatterns = { "/Students/*" })
public class StudentListController extends HttpServlet {
    public static final Logger LOGGER = Logger.getLogger("StudentDataAccessLogger");
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String urlpath = request.getPathInfo();
        if (urlpath == null)
            list(request, response, 1);
        else {
            String[] urlparts = urlpath.split("/");
            if ("NewEntry".equals(urlparts[1])) {
                createProcess(request, response);
            } else if ("edit".equals(urlparts[2])) {
                editProcess(request, response, Integer.parseInt(urlparts[1]));
            } else if ("delete".equals(urlparts[2])) {
                deleteProcess(request, response, Integer.parseInt(urlparts[1]));
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String urlpath = request.getPathInfo();
        if (urlpath == null)
            list(request, response, 1);
        else {
            String[] urlparts = urlpath.split("/");
            if ("NewEntry".equals(urlparts[1])) {
                create(request, response);
            } else if ("edit".equals(urlparts[2])) {
                edit(request, response, Integer.parseInt(urlparts[1]));
            }
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response, int page) throws ServletException, IOException {
        StudentServices stdservice = new StudentServices();
        try {
            request.setAttribute("students", stdservice.fetch(page));
            request.getServletContext().getRequestDispatcher("/WEB-INF/views/students.jsp").forward(request, response);
        } catch (DataException e) {
            LOGGER.log(Level.SEVERE,e.getMessage());
        }
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/WEB-INF/views/newEntry.jsp").forward(request, response);
    }

    private void createProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        int roll = Integer.parseInt(request.getParameter("roll"));
        Student s = new Student(name, address, roll);

        StudentServices stdServices = new StudentServices();
        try {
            stdServices.addNew(s);
            response.sendRedirect("/Students");
        } catch (DataException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response, int roll) throws ServletException, IOException {
        StudentServices stdservice = new StudentServices();
        try {
            request.setAttribute("student", stdservice.fetchById(roll));
            request.getRequestDispatcher("/WEB-INF/views/editEntry.jsp").forward(request, response);
        } catch (DataException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }

    private void editProcess(HttpServletRequest request, HttpServletResponse response, int roll) throws ServletException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        Student s = new Student(name, address, roll);

        StudentServices stdServices = new StudentServices();
        try {
            stdServices.update(s);
            response.sendRedirect("/Students");
        } catch (DataException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }

    private void deleteProcess(HttpServletRequest request, HttpServletResponse response, int roll) throws ServletException, IOException {
        StudentServices stdServices = new StudentServices();
        try {
            stdServices.delete(roll);
            response.sendRedirect("/Students");
        } catch (DataException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }
}
