package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.services.EmployeeServices;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by grishma on 1/19/16.
 */
@WebServlet({ "/employees/*" })
public class EmployeesController extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path == null) {
            Integer pageNo = 1;
            fetch(request, response, pageNo);
        }
        else {
            String[] parts = path.split("/");
            if (parts[1].equals("create")) {
                create(request, response);
            }
            else if (parts[1].equals("page")) {
                Integer pageNo = Integer.parseInt(parts[2]);
                fetch(request, response, pageNo);
            }
            else if (parts[2].equals("edit")) {
                int id = Integer.parseInt(parts[1]);
                edit(request, response, id);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();

        if (path == null) {
            request.getServletContext().getRequestDispatcher("/WEB-INF/views/employees/index.jsp").forward(request, response);
        }
        else {
            String[] parts = path.split("/");
            if (parts[1].equals("createProcess")) {
                createProcess(request, response);
            }
            else if (parts[2].equals("editProcess")) {
                int id = Integer.parseInt(parts[1]);
                editProcess(request, response, id);
            }
            else if (parts[2].equals("deleteProcess")) {
                int id = Integer.parseInt(parts[1]);
                deleteProcess(request, response, id);
            }
        }
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/WEB-INF/views/employees/new.jsp").forward(request, response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response, Integer id) throws ServletException, IOException {
        try{
            Employee employee = new Employee();
            EmployeeServices employeeServices = new EmployeeServices();
            employee = employeeServices.fetchById(id);
            request.setAttribute("employee", employee);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/employees/edit.jsp");
            dispatcher.forward(request, response);
        }
        catch (DataException e) {
            e.printStackTrace();
            //needs to be handled
        }

    }

    private void createProcess(HttpServletRequest request, HttpServletResponse response) {
        try {
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String designation = request.getParameter("designation");
            String phone = request.getParameter("phone");
            EmployeeServices employeeServices = new EmployeeServices();

            Employee employee = new Employee();
            employee.setName(name);
            employee.setAddress(address);
            employee.setDesignation(designation);
            employee.setPhone(phone);

            employeeServices.create(employee);
            response.sendRedirect("/employees");
        } catch (DataException e) {
            e.printStackTrace();
            // check here for error and do required redirection and message display
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editProcess(HttpServletRequest request, HttpServletResponse response, Integer id) {
        try{

            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String designation = request.getParameter("designation");
            String phone = request.getParameter("phone");

            Employee employee = new Employee();
            employee.setName(name);
            employee.setAddress(address);
            employee.setDesignation(designation);
            employee.setPhone(phone);

            EmployeeServices employeeServices = new EmployeeServices();
            employeeServices.edit(employee, id);
            response.sendRedirect("/employees");
        }
        catch (DataException e) {
            e.printStackTrace();
            //needs to be handled
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void deleteProcess(HttpServletRequest request, HttpServletResponse response, Integer id) {
        try {
            EmployeeServices employeeServices = new EmployeeServices();
            employeeServices.delete(id);
            response.sendRedirect("/employees");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DataException e) {
            e.printStackTrace();
        }
    }

    private void fetch(HttpServletRequest request, HttpServletResponse response, Integer pageNo) throws IOException {
        Integer pageLimit = 2;
        pageNo --;
        System.out.println(pageNo);
        try {
            List<Employee> employeeList = new ArrayList<Employee>();
            EmployeeServices employeeServices = new EmployeeServices();
            employeeList = employeeServices.fetch(pageLimit, pageNo);
            request.setAttribute("employeeList", employeeList);
            request.getServletContext().getRequestDispatcher("/WEB-INF/views/employees/index.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}