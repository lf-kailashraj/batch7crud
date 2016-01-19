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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by pratishshr on 1/14/16.
 */
@WebServlet(name = "EmployeeController", urlPatterns = { "/employees/*" })

public class EmployeeController extends HttpServlet {
    private static final String ERROR_PAGE = "/WEB-INF/views/error.jsp";
    private static final String MESSAGE = "message";

    private Logger logger = Logger.getLogger("EmployeeControllerLog");
    private EmployeeService employeeService;

    public EmployeeController() throws DataException {
        employeeService = new EmployeeService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String urlPath = request.getPathInfo();

        if (urlPath == null || "/".equals(urlPath)) {
            int page = 1;

            if (request.getParameter("page") != null) {
                try {
                    page = Integer.parseInt(request.getParameter("page"));
                } catch (NumberFormatException e) {
                    logger.log(Level.SEVERE, e.getMessage());

                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    request.setAttribute(MESSAGE, e.getMessage());

                    RequestDispatcher view = request.getRequestDispatcher(ERROR_PAGE);
                    view.forward(request, response);
                }
            } else {
                page = 1;
            }
            list(request, response, page);

        } else {
            String[] paths = urlPath.split("/");

            if ("create".equals(paths[1])) {
                create(request, response);
            } else if (paths[2].equals("edit")) {
                try {
                    int id = Integer.parseInt(paths[1]);
                    edit(request, response, id);
                } catch (NumberFormatException e) {
                    logger.log(Level.SEVERE, e.getMessage());

                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    request.setAttribute(MESSAGE, e.getMessage());

                    RequestDispatcher view = request.getRequestDispatcher(ERROR_PAGE);
                    view.forward(request, response);
                }
            }
        }
    }

    @Override protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String urlPath = request.getPathInfo();

        if (urlPath != null || !"/".equals(urlPath)) {
            String[] paths = urlPath.split("/");
            if ("create".equals(paths[1])) {
                createProcess(request, response);
            } else if ("edit".equals(paths[2])) {
                try {
                    int id = Integer.parseInt(paths[1]);
                    editProcess(request, response, id);
                } catch (NumberFormatException e) {
                    logger.log(Level.SEVERE, e.getMessage());

                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    request.setAttribute(MESSAGE, e.getMessage());

                    RequestDispatcher view = request.getRequestDispatcher(ERROR_PAGE);
                    view.forward(request, response);
                }
            } else if ("delete".equals(paths[2])) {
                try {
                    int id = Integer.parseInt(paths[1]);
                    deleteProcess(request, response, id);
                } catch (NumberFormatException e) {
                    logger.log(Level.SEVERE, e.getMessage());

                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    request.setAttribute(MESSAGE, e.getMessage());

                    RequestDispatcher view = request.getRequestDispatcher(ERROR_PAGE);
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
            logger.log(Level.SEVERE, e.getMessage());

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            request.setAttribute(MESSAGE, e.getMessage());

            RequestDispatcher view = request.getRequestDispatcher(ERROR_PAGE);
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
            logger.log(Level.SEVERE, e.getMessage());

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            request.setAttribute(MESSAGE, e.getMessage());

            RequestDispatcher view = request.getRequestDispatcher(ERROR_PAGE);
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
            logger.log(Level.SEVERE, e.getMessage());

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            request.setAttribute(MESSAGE, e.getMessage());

            RequestDispatcher view = request.getRequestDispatcher(ERROR_PAGE);
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
            logger.log(Level.SEVERE, e.getMessage());

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            request.setAttribute(MESSAGE, e.getMessage());

            RequestDispatcher view = request.getRequestDispatcher(ERROR_PAGE);
            view.forward(request, response);
        }

    }

    private void deleteProcess(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
        try {
            employeeService.deleteEmployee(id);
            response.sendRedirect(request.getContextPath() + "/employees");
        } catch (DataException e) {
            logger.log(Level.SEVERE, e.getMessage());

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            request.setAttribute(MESSAGE, e.getMessage());

            RequestDispatcher view = request.getRequestDispatcher(ERROR_PAGE);
            view.forward(request, response);
        }
    }
}