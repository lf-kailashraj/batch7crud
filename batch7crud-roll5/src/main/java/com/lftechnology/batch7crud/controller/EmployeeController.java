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
    EmployeeService employeeService = new EmployeeService();
    Employee emp = new Employee();
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String path = request.getPathInfo();
        if (path != null) {

            String[] pathParts = path.split("/");
            /*
             * for (String s : pathParts) { System.out.println("parts: " + s); }
             */
            if (pathParts[1].equals("create"))
                create(request, response);

            else if (pathParts[2].equals("edit")) {
                int id = Integer.parseInt(pathParts[1]);
                edit(request, response, id);
            }
            
            else if (pathParts[1].equals("page")) {
                int pageNo = Integer.parseInt(pathParts[2]);
                fetchData(request, response, pageNo-1);
            }

        }

        else {
            int pageNo = 1;
            fetchData(request, response, pageNo-1);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-enerated method stub
        // doGet(request, response);
        String path = request.getPathInfo();
        if (path != null) {
            String[] pathParts = path.split("/");

            if (pathParts[1].equals("createProcess")) {
                createProcess(request, response);

            }

            else if (pathParts[2].equals("editProcess")) {
                int id = Integer.parseInt(pathParts[1]);
                editProcess(request, response, id);

            }

            else if (pathParts[2].equals("deleteProcess")) {
                int id = Integer.parseInt(pathParts[1]);
                deleteProcess(request, response, id);
            }

        } else {
            int pageNo = 1;
            fetchData(request, response, pageNo-1);
        }

    }

    private void deleteProcess(HttpServletRequest request, HttpServletResponse response, int id) {
        // TODO Auto-generated method stub
        try {
            employeeService.deleteById(id);

            response.sendRedirect("/batch7crud-roll5/employees");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void edit(HttpServletRequest request, HttpServletResponse response, int id) {
        // TODO Auto-generated method stub
        try {
            emp = employeeService.fetchById(id);
            request.setAttribute("employee", emp);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/editEmployee.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void editProcess(HttpServletRequest request, HttpServletResponse response, int id) {
        // TODO Auto-generated method stub
        try {
            emp.setFirstName(request.getParameter("firstName"));
            emp.setLastName(request.getParameter("lastName"));
            emp.setDepartment(request.getParameter("department"));
            emp.setAddress(request.getParameter("address"));

            employeeService.edit(emp, id);

            response.sendRedirect("/batch7crud-roll5/employees");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }

    }

    private void create(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/createEmployee.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void createProcess(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        try {
            emp.setFirstName(request.getParameter("firstName"));
            emp.setLastName(request.getParameter("lastName"));
            emp.setDepartment(request.getParameter("department"));
            emp.setAddress(request.getParameter("address"));

            boolean status = employeeService.create(emp);

            if (status == true) {
                response.sendRedirect("/batch7crud-roll5/employees");

            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    private void fetchData(HttpServletRequest request, HttpServletResponse response, int pageNo) {
        // TODO Auto-generated method stub
        try {
            List<Employee> employeeList = new ArrayList<Employee>();
            employeeList = employeeService.fetch(pageNo);
            request.setAttribute("employeeList", employeeList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/employee.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
