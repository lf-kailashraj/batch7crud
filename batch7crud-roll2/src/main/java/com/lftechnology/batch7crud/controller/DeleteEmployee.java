package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by romit on 1/15/16.
 */
@WebServlet(name = "DeleteEmployee",urlPatterns = {"/Delete"})
public class DeleteEmployee extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int deleteId = Integer.parseInt(request.getParameter("deleteId"));
        try {
            EmployeeService employeeService = new EmployeeService();
            employeeService.deleteEmployee(deleteId);
        }catch (Exception e){
            e.printStackTrace();
        }
        response.sendRedirect("/Display");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
