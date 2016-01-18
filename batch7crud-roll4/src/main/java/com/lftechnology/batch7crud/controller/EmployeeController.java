package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.service.EmployeeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by pratishshr on 1/14/16.
 */
@WebServlet(name = "EmployeeController", urlPatterns = {"/employees/*"})
public class EmployeeController extends HttpServlet {
    private EmployeeService employeeService;

    public EmployeeController() throws DataException {
        employeeService = new EmployeeService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String urlPath = request.getPathInfo();

        if (urlPath == null || urlPath.equals("/")) {
            list(request, response, 0);

        } else {
            String[] paths = urlPath.split("/");
            System.out.println(Arrays.toString(paths));
            if (paths[1].equals("create")) {
                create(request, response);
            } else if (paths[2].equals("edit")) {
                int id = Integer.parseInt(paths[1]);
                edit(request, response, id);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String urlPath = request.getPathInfo();

        if (urlPath == null || urlPath.equals("/")) {

        } else {
            String[] paths = urlPath.split("/");
            if (paths[1].equals("create")) {
                createProcess(request, response);
            } else if (paths[2].equals("edit")) {
                int id = Integer.parseInt(paths[1]);
                editProcess(request, response, id);
            } else if (paths[2].equals("delete")) {
                int id = Integer.parseInt(paths[1]);
                deleteProcess(request, response, id);
            }
        }
    }


    private void list(HttpServletRequest request, HttpServletResponse response, int page) throws ServletException, IOException {
        try {
            String forward = "/WEB-INF/views/employees.jsp";
            request.setAttribute("employees", employeeService.fetch());

            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);

        } catch (DataException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            request.setAttribute("message", e.getMessage());

            RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/error.jsp");
            view.forward(request, response);

        }
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "/WEB-INF/views/create.jsp";

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    private void createProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String station = request.getParameter("station");

        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setStation(station);

        try {
            employeeService.save(employee);
            response.sendRedirect(request.getContextPath() + "/employees");
        } catch (DataException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/error.jsp");
            view.forward(request, response);
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
        try {
            String forward = "/WEB-INF/views/edit.jsp";
            request.setAttribute("employee", employeeService.fetch(id));

            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
        } catch (DataException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/error.jsp");
            view.forward(request, response);
        }

    }

    private void editProcess(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String station = request.getParameter("station");

        Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setStation(station);

        try {
            employeeService.update(employee);
            response.sendRedirect(request.getContextPath() + "/employees");
        } catch (DataException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/error.jsp");
            view.forward(request, response);
        }

    }

    private void deleteProcess(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
        try {
            employeeService.deleteEmployee(id);
            response.sendRedirect(request.getContextPath() + "/employees");
        } catch (DataException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/error.jsp");
            view.forward(request, response);
        }
    }
}