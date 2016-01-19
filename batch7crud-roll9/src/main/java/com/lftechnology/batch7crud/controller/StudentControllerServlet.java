package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.services.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by sanjay on 1/18/16.
 */
@WebServlet("/students/*")
public class StudentControllerServlet extends HttpServlet {
    private Logger logger = Logger.getLogger("appLogger");
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String[] pathParts=null;
        if(pathInfo !=null){
            pathParts = pathInfo.split("/");
            System.out.println("splitting the path");
        }
        else{
            System.out.println("no the path to split null");
        }
       if(pathParts.length == 2 && pathParts[1].equals("create")){
            createProcess(request, response);
       }
       else if(pathParts.length == 3 && pathParts[2].equals("delete"))
       {
           if(pathParts[1].matches("[-+]?\\d*\\.?\\d+"))
           {
               System.out.println("center in a number");
               deleteProcess(request,response,Integer.parseInt(pathParts[1]));
           }
           else{
               System.out.println("!!!center in a not number");
           }
       }
       else if(pathParts.length == 3 && pathParts[2].equals("edit"))
       {
           if(pathParts[1].matches("[-+]?\\d*\\.?\\d+"))
           {
               System.out.println("center in a number---edit");
               editProcess(request, response, Integer.parseInt(pathParts[1]));
           }
           else{
               System.out.println("!!!center in a not number");
           }

       }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String[] pathParts=null;
        if(pathInfo !=null){
            pathParts = pathInfo.split("/");
            System.out.println("splitting the path");
        }
        else{
            System.out.println("no the path to split null");
        }
        if(pathInfo == null) {
            if(request.getParameter("page") == null) {
                int page = 1;
                list(request, response, page);
            }
            else{
                list(request, response, Integer.parseInt(request.getParameter("page")));
            }
        }
        else if(pathParts.length == 2 && pathParts[1].equals("create")){
            create(request,response);
        }
        else if(pathParts.length == 3 && pathParts[2].equals("edit"))
        {
            if(pathParts[1].matches("[-+]?\\d*\\.?\\d+"))
            {
                System.out.println("center in a number");
                edit(request, response, Integer.parseInt(pathParts[1]));
            }
            else{
                System.out.println("!!!center in a not number");
            }

        }
    }


    private void list(HttpServletRequest request, HttpServletResponse response, int page) throws ServletException, IOException {
        System.out.println("fetch the data of students");
        List<Student> stdList = new ArrayList<Student>();
        try {
            int LIMIT = 20;
            StudentService studentService = new StudentService();
            stdList = studentService.fetch(page,LIMIT);
            int totalCount = studentService.studentCount();
            request.setAttribute("studentList", stdList);
            request.setAttribute("page", page);
            request.setAttribute("totalPages",totalCount/LIMIT);
            request.getServletContext().getRequestDispatcher("/WEB-INF/views/display.jsp").forward(request, response);
        } catch (DataException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
            System.out.println("--------------" + ex);
        }
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/WEB-INF/views/newStudent.jsp").forward(request, response);
    }

    private void createProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student s=new Student();
        s.setFirstName(request.getParameter("fname"));
        s.setMiddleName(request.getParameter("mname"));
        s.setLastName(request.getParameter("lname"));
        s.setAddress(request.getParameter("address"));
        s.setGrade(Integer.parseInt(request.getParameter("grade")));
        try {
            StudentService studentService = new StudentService();
            studentService.save(s);
            response.sendRedirect(request.getContextPath());
        }catch (DataException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
            System.out.println("--------------" + ex);
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
        try {
            StudentService studentService = new StudentService();
            Student s = studentService.fetchById(id);
            request.setAttribute("student",s);
            System.out.println(s.getAddress());
            request.getServletContext().getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(request, response);
        }catch (DataException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
            System.out.println("--------------" + ex);
        }
    }

    private void editProcess(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
        try {
            StudentService studentService = new StudentService();
            Student s = new Student();
            s.setId(id);
            s.setFirstName(request.getParameter("fname"));
            s.setMiddleName(request.getParameter("mname"));
            s.setLastName(request.getParameter("lname"));
            s.setAddress(request.getParameter("address"));
            s.setGrade(Integer.parseInt(request.getParameter("grade")));
            studentService.edit(s);
            System.out.println(s.getAddress());
            response.sendRedirect(request.getContextPath()+"/students");
        }catch (DataException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
            System.out.println("--------------" + ex);
        }
    }

    private void deleteProcess(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
        try {
            StudentService studentService = new StudentService();
            studentService.delete(id);
            response.sendRedirect(request.getContextPath()+"/students");
        }catch (DataException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
            System.out.println("--------------" + ex);
        }
    }





}
