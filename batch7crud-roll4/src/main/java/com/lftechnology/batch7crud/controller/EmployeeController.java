package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.service.EmployeeService;
import jdk.nashorn.internal.ir.RuntimeNode;

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
@WebServlet(name = "EmployeeController", urlPatterns = { "/employees/*" })

public class EmployeeController extends HttpServlet {
    private EmployeeService employeeService;

    public EmployeeController() throws DataException {
        employeeService = new EmployeeService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String urlPath = request.getPathInfo();

        if (urlPath == null || urlPath.equals("/")) {
            int page = 1;

            if (request.getParameter("page") != null) {
                try {
                    page = Integer.parseInt(request.getParameter("page"));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    request.setAttribute("message", e.getMessage());

                    RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/error.jsp");
                    view.forward(request, response);
                }
            } else {
                page = 1;
            }
            list(request, response, page);

        } else {
            String[] paths = urlPath.split("/");
            System.out.println(Arrays.toString(paths));
            if (paths[1].equals("create")) {
                create(request, response);
            } else if (paths[2].equals("edit")) {
                try {
                    int id = Integer.parseInt(paths[1]);
                    edit(request, response, id);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    request.setAttribute("message", e.getMessage());

                    RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/error.jsp");
                    view.forward(request, response);
                }
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String urlPath = request.getPathInfo();

        if(urlPath != null || !urlPath.equals("/")){
            String[] paths = urlPath.split("/");
            if (paths[1].equals("create")) {
                createProcess(request, response);
            } else if (paths[2].equals("edit")) {
                try {
                    int id = Integer.parseInt(paths[1]);
                    editProcess(request, response, id);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    request.setAttribute("message", e.getMessage());

                    RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/error.jsp");
                    view.forward(request, response);
                }
            } else if (paths[2].equals("delete")) {
                try {
                    int id = Integer.parseInt(paths[1]);
                    deleteProcess(request, response, id);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    request.setAttribute("message", e.getMessage());

                    RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/error.jsp");
                    view.forward(request, response);
                }

            }
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response, int page) throws ServletException, IOException {
        try {
            String forward = "/WEB-INF/views/employees.jsp";

            int noOfRecords = employeeService.fetchNoOfRecords();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / 20);
            int recordLimit = 20;

            request.setAttribute("employees", employeeService.fetch(page, recordLimit));
            request.setAttribute("currentPage", page);
            request.setAttribute("noOfPages", noOfPages);

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
            request.setAttribute("employee", employeeService.fetchById(id));

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