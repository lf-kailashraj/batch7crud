package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.services.EmployeeService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/employees/*")
public class EmployeeController extends CustomHttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String EMPLOYEE_LIST_URL = "/batch7crud-roll5/employees";
    private static final Logger LOGGER = Logger.getLogger("EmployeeControllerLog");

    private static EmployeeService employeeService = new EmployeeService();
    private static Employee emp = new Employee();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();

        if (path != null) {
            String[] pathParts = path.split("/");

            if (pathParts.length == 2 && "create".equals(pathParts[1]))
                create(request, response);

            else if (pathParts.length == 3 && "edit".equals(pathParts[2])) {
                edit(request, response);
            }

            else
                show404(request, response);
        }

        else {
            fetchData(request, response);
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
                editProcess(request, response);
            }

            else if ("deleteProcess".equals(pathParts[2])) {
                deleteProcess(request, response);
            }

        } else {
            fetchData(request, response);
        }

    }

    private void deleteProcess(HttpServletRequest request, HttpServletResponse response) {
        try {
            int employeeId = parameterValueAsInt(request, 2);
            employeeService.deleteById(employeeId);

            response.sendRedirect(EMPLOYEE_LIST_URL);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            show500(request, response, e);
        }

    }

    private void edit(HttpServletRequest request, HttpServletResponse response) {
        try {
            int employeeId = parameterValueAsInt(request, 2);
            request.setAttribute("employee", employeeService.fetchById(employeeId));
            request.getRequestDispatcher("/WEB-INF/views/editEmployee.jsp").forward(request, response);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            show500(request, response, e);
        }
    }

    private void editProcess(HttpServletRequest request, HttpServletResponse response) {
        try {
            emp.setFirstName(request.getParameter("firstName"));
            emp.setLastName(request.getParameter("lastName"));
            emp.setDepartment(request.getParameter("department"));
            emp.setAddress(request.getParameter("address"));

            int employeeId = parameterValueAsInt(request, 2);
            employeeService.edit(emp, employeeId);

            response.sendRedirect(EMPLOYEE_LIST_URL);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            show500(request, response, e);
        }

    }

    private void create(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("/WEB-INF/views/createEmployee.jsp").forward(request, response);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            show500(request, response, e);
        }

    }

    private void createProcess(HttpServletRequest request, HttpServletResponse response) {
        try {
            emp.setFirstName(request.getParameter("firstName"));
            emp.setLastName(request.getParameter("lastName"));
            emp.setDepartment(request.getParameter("department"));
            emp.setAddress(request.getParameter("address"));

            response.sendRedirect(EMPLOYEE_LIST_URL);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            show500(request, response, e);
        }

    }

    private void fetchData(HttpServletRequest request, HttpServletResponse response) {
        List<Employee> employeeList = new ArrayList<Employee>();

        try {
            int employeeCount = employeeService.count();
            int pageNo = pageNumber(request);

            employeeList = employeeService.fetch(pageNo - 1);

            request.setAttribute("employeeList", employeeList);
            request.setAttribute("pageNo", pageNo);
            request.setAttribute("noOfEmployee", employeeCount);
            request.setAttribute("noEmpInPage", 10);

            request.getRequestDispatcher("/WEB-INF/views/employee.jsp").forward(request, response);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            show500(request, response, e);
        }

    }

}
