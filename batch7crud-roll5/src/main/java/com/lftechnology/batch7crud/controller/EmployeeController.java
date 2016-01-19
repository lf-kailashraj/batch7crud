package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.services.EmployeeService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmployeeController
 */

@WebServlet("/employees/*")
public class EmployeeController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    EmployeeService employeeService = new EmployeeService();
    Employee emp = new Employee();

    public static String employeeListUrl = "/batch7crud-roll5/employees";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path != null) {
            String[] pathParts = path.split("/");

            if ("create".equals(pathParts[1]))
                create(request, response);

            else if ("edit".equals(pathParts[2])) {
                int id = Integer.parseInt(pathParts[1]);
                edit(request, response, id);
            }

            else if ("page".equals(pathParts[1])) {
                int pageNo = Integer.parseInt(pathParts[2]);
                fetchData(request, response, pageNo - 1);
            }

        }

        else {
            int pageNo = 1;
            fetchData(request, response, pageNo - 1);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path != null) {
            String[] pathParts = path.split("/");

            if ("createProcess".equals(pathParts[1])) {
                createProcess(request, response);

            }

            else if ("editProcess".equals(pathParts[2])) {
                int id = Integer.parseInt(pathParts[1]);
                editProcess(request, response, id);

            }

            else if ("deleteProcess".equals(pathParts[2])) {
                int id = Integer.parseInt(pathParts[1]);
                deleteProcess(request, response, id);
            }

        } else {
            int pageNo = 1;
            fetchData(request, response, pageNo - 1);
        }

    }

    private void deleteProcess(HttpServletRequest request, HttpServletResponse response, int id) {
        try {
            employeeService.deleteById(id);

            response.sendRedirect(employeeListUrl);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void edit(HttpServletRequest request, HttpServletResponse response, int id) {
        try {
            emp = employeeService.fetchById(id);
            request.setAttribute("employee", emp);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/editEmployee.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void editProcess(HttpServletRequest request, HttpServletResponse response, int id) {
        try {
            emp.setFirstName(request.getParameter("firstName"));
            emp.setLastName(request.getParameter("lastName"));
            emp.setDepartment(request.getParameter("department"));
            emp.setAddress(request.getParameter("address"));

            employeeService.edit(emp, id);

            response.sendRedirect(employeeListUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void create(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/createEmployee.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void createProcess(HttpServletRequest request, HttpServletResponse response) {
        try {
            emp.setFirstName(request.getParameter("firstName"));
            emp.setLastName(request.getParameter("lastName"));
            emp.setDepartment(request.getParameter("department"));
            emp.setAddress(request.getParameter("address"));

            boolean status = employeeService.create(emp);

            if (status == true) {
                response.sendRedirect(employeeListUrl);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void fetchData(HttpServletRequest request, HttpServletResponse response, int pageNo) {
        try {
            List<Employee> employeeList = new ArrayList<Employee>();
            employeeList = employeeService.fetch(pageNo);
            request.setAttribute("employeeList", employeeList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/employee.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
