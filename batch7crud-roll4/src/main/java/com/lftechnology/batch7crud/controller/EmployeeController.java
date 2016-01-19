package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.service.EmployeeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by pratishshr on 1/14/16.
 */
@WebServlet(name = "EmployeeController", urlPatterns = { "/employees/*" })

public class EmployeeController extends CustomHttpServlet {
    private static final String ERROR_PAGE = "/WEB-INF/views/error.jsp";
    private static final String MESSAGE = "message";
    private static final String EMPLOYEE_LISTING_PAGE = "/employees";

    private Logger logger = Logger.getLogger("EmployeeControllerLog");
    private EmployeeService employeeService;

    public EmployeeController() throws DataException {
        employeeService = new EmployeeService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] parameters = parameterValues(request);

        if (parameters.length == 2 && "employees".equals(parameters[1])) {
            list(request, response);
        } else if (parameters.length == 3 && "create".equals(parameters[2])) {
            create(request, response);
        } else if (parameters.length == 4 && "edit".equals(parameters[3])) {
            edit(request, response);
        } else {
            show404(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] parameters = parameterValues(request);

        if (parameters.length == 3 && "create".equals(parameters[2])) {
            createProcess(request, response);
        } else if (parameters.length == 4 && "edit".equals(parameters[3])) {
            editProcess(request, response);
        } else if (parameters.length == 4 && "delete".equals(parameters[3])) {
            deleteProcess(request, response);
        } else {
            show404(request, response);
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String forward = "/WEB-INF/views/employees.jsp";

            int page = pageNumber(request);
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
            show500(request, response, e);
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
            response.sendRedirect(request.getContextPath() + EMPLOYEE_LISTING_PAGE);
        } catch (DataException e) {
            logger.log(Level.SEVERE, e.getMessage());
            show500(request, response, e);
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int employeeId = parameterValueAsInt(request, 2);
            String forward = "/WEB-INF/views/edit.jsp";
            request.setAttribute("employee", employeeService.fetchById(employeeId));

            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
        } catch (DataException e) {
            logger.log(Level.SEVERE, e.getMessage());
            show500(request, response, e);
        }

    }

    private void editProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int employeeId = parameterValueAsInt(request, 2);
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String station = request.getParameter("station");

            Employee employee = new Employee();
            employee.setId(employeeId);
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setStation(station);

            employeeService.update(employee);
            response.sendRedirect(request.getContextPath() + EMPLOYEE_LISTING_PAGE);
        } catch (DataException e) {
            logger.log(Level.SEVERE, e.getMessage());
            show500(request, response, e);
        }

    }

    private void deleteProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int employeeId = parameterValueAsInt(request, 2);
            employeeService.deleteEmployee(employeeId);
            response.sendRedirect(request.getContextPath() + EMPLOYEE_LISTING_PAGE);
        } catch (DataException e) {
            logger.log(Level.SEVERE, e.getMessage());
            show500(request, response, e);
        }
    }
}