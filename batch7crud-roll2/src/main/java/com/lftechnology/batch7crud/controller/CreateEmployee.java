package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.model.Employee;
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
@WebServlet(name = "CreateEmployee", urlPatterns = {"/Create"})
public class CreateEmployee extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String contact = request.getParameter("contact");

        Employee employee = new Employee();
        employee.setName(name);
        employee.setAddress(address);
        employee.setEmail(email);
        employee.setContact(contact);
        try{
            EmployeeService employeeService = new EmployeeService();
            employeeService.addEmployee(employee);
            response.sendRedirect("/Display");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/form.jsp").forward(request,response);
    }
}
