package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by romit on 1/14/16.
 */
@WebServlet(name = "DisplayEmployee", urlPatterns = {"/Display"})
public class DisplayEmployee extends HttpServlet {
    public EmployeeService employeeService;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> empList;
        try {
            employeeService = new EmployeeService();
            empList = employeeService.getEmployees(10);
            request.setAttribute("employeeList", empList);
            request.getRequestDispatcher("/WEB-INF/views/display.jsp").forward(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
