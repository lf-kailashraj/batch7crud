package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Student;
import com.lftechnology.batch7crud.service.StudentServices;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
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
@WebServlet(name = "StudentListController", urlPatterns = { "/Students/*" }) public class StudentListController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger("StudentLogger");

    @Override protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] urlpath = urlParts(request);
        try {
            if(urlpath.length == 3 && "NewEntry".equals(urlpath[2]))
                createProcess(request, response);
            else if(urlpath.length == 4 && "edit".equals(urlpath[3]))
                editProcess(request, response, Integer.parseInt(urlpath[2]));
            else if(urlpath.length == 4 && "delete".equals(urlpath[3]))
                deleteProcess(request, response, Integer.parseInt(urlpath[2]));
            else
                showErrorPage(request,response);
        } catch (DataException | ServletException | IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] urlpath = urlParts(request);
        try {
            if (urlpath.length == 2 )
                list(request, response);
            else if(urlpath.length == 3 && "NewEntry".equals(urlpath[2]))
                create(request, response);
            else if(urlpath.length == 4 && "edit".equals(urlpath[3]))
                edit(request, response, Integer.parseInt(urlpath[2]));
            else if(urlpath.length == 3)
                show(request,response);
            else
                showErrorPage(request, response);
        } catch (DataException | ServletException | IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, DataException {
        StudentServices stdservice = new StudentServices();
        int pageSize = 3;
        int page = 1;
        try{
            page = getPageNumber(request);
        }
        catch(NumberFormatException e){
            showErrorPage(request, response);
        }
        int totalStudents = stdservice.fetchTotal();

        if(page !=1 && page > Math.ceil(totalStudents/(float)pageSize)){
            showErrorPage(request,response);
        }
        request.setAttribute("students", stdservice.fetch(page,pageSize));
        request.setAttribute("pageSize",pageSize);
        request.setAttribute("totalStudents",totalStudents);
        request.setAttribute("pageNum",page);

        request.getServletContext().getRequestDispatcher("/WEB-INF/views/students.jsp").forward(request, response);

    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/WEB-INF/views/newEntry.jsp").forward(request, response);
    }

    private void createProcess(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, DataException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String roll = request.getParameter("roll");
        try{

            int rollNum = Integer.parseInt(roll);
            Student s = new Student();
            s.setName(name);
            s.setAddress(address);
            s.setRoll(rollNum);

            StudentServices stdServices = new StudentServices();
            stdServices.addNew(s);
            response.sendRedirect("/Students");
        }catch(NumberFormatException e){
            request.setAttribute("error","invalid roll");
            request.getServletContext().getRequestDispatcher("/WEB-INF/views/newEntry.jsp").forward(request, response);
        }


    }

    private void edit(HttpServletRequest request, HttpServletResponse response, int id)
            throws ServletException, IOException, DataException {
        StudentServices stdservice = new StudentServices();

        request.setAttribute("student", stdservice.fetchById(id));
        request.getRequestDispatcher("/WEB-INF/views/editEntry.jsp").forward(request, response);

    }

    private void editProcess(HttpServletRequest request, HttpServletResponse response, int id)
            throws ServletException, IOException, DataException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        int roll = Integer.parseInt(request.getParameter("roll"));
        Student s = new Student();
        s.setName(name);
        s.setAddress(address);
        s.setRoll(roll);
        s.setId(id);

        StudentServices stdServices = new StudentServices();
        stdServices.update(s, id);
        response.sendRedirect("/Students");

    }

    private void deleteProcess(HttpServletRequest request, HttpServletResponse response, int roll)
            throws ServletException, IOException, DataException {
        StudentServices stdServices = new StudentServices();
        stdServices.delete(roll);
        response.sendRedirect("/Students");

    }

    private void show(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, DataException {
        try {
            int id = urlInteger(request, 2);
            StudentServices stdService = new StudentServices();
            Student student = stdService.fetchById(id);
            request.setAttribute("student", student);
            request.getRequestDispatcher("/WEB-INF/views/show.jsp").forward(request, response);
        } catch (NumberFormatException e) {
           System.out.println("Number Format Exception");
        }
    }

    public void showErrorPage(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/error.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public String[] urlParts(HttpServletRequest request) {
        String urlPath = request.getRequestURI().substring(request.getContextPath().length());
        return urlPath.split("/");
    }

    public int urlInteger(HttpServletRequest request, int index) {
        String[] paths = urlParts(request);
        return Integer.parseInt(paths[index]);
    }

    public int getPageNumber(HttpServletRequest request) {
        if (request.getParameter("page") != null) {
            return Integer.parseInt(request.getParameter("page"));
        } else {
            return 1;
        }
    }
}
