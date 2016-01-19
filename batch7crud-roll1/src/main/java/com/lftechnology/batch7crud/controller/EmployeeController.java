package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.service.EmployeeService;
import com.lftechnology.batch7crud.util.TypeCaster;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmployeeController
 */
@WebServlet({"/employees/*"})
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    EmployeeService employeeService = new EmployeeService();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String requestInfo = request.getPathInfo();
        System.out.println("requestInfo :" + requestInfo);

        if(requestInfo == null){
            list(request,response,1);
        }else {

            String[] pathParts = requestInfo.split("/");

            if(pathParts[0].equals("") && pathParts[1].equals("create")){
                create(request,response);
            }else if(pathParts[2].equals("edit")){
                int id = TypeCaster.toInt(pathParts[1]);
                edit(request,response,id);
            }
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestInfo = request.getPathInfo();
        System.out.println("requestInfo :" + requestInfo);

        String[] pathParts = requestInfo.split("/");

        if(pathParts[1].equals("create")){
            createProcess(request, response);
        }else if(pathParts[2].equals("edit")){
            int id = TypeCaster.toInt(pathParts[1]);
            editProcess(request,response,id);
        }else if(pathParts[2].equals("delete")){
            int id = TypeCaster.toInt(pathParts[1]);
            deleteProcess(request,response,id);
        }
	}

    private void list(HttpServletRequest request, HttpServletResponse response, int page) throws ServletException, IOException {

        try {
            List<Employee> employees = employeeService.fetch();
            request.setAttribute("employees", employees);
            request.getServletContext().getRequestDispatcher("/WEB-INF/views/employee/list.jsp").forward(request, response);
        } catch (DataException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            request.setAttribute("message", e.getMessage());
            request.getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/WEB-INF/views/employee/create.jsp").forward(request, response);
    }

    private void createProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = TypeCaster.toInt(request.getParameter("id"));
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullName");
        String department = request.getParameter("department");
        String address = request.getParameter("address");
        Employee employee = new Employee(id,userName,password,fullName,department,address);

        try {
            employeeService.create(employee);
            response.sendRedirect("/employees");
        } catch (DataException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            request.setAttribute("message",e.getMessage());
            request.getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
        try {
            Employee employee = employeeService.fetchById(id);
            request.setAttribute("employee", employee);
            request.getServletContext().getRequestDispatcher("/WEB-INF/views/employee/edit.jsp").forward(request, response);
        } catch (DataException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            request.setAttribute("message", e.getMessage());
            request.getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }

    private void editProcess(HttpServletRequest request, HttpServletResponse response,int id) throws ServletException, IOException {
        id = TypeCaster.toInt(request.getParameter("id"));
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullName");
        String department = request.getParameter("department");
        String address = request.getParameter("address");
        Employee employee = new Employee(id,userName,password,fullName,department,address);

        try {
            employeeService.update(employee);
            response.sendRedirect("/employees");
        } catch (DataException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            request.setAttribute("message", e.getMessage());
            request.getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }

    private void deleteProcess(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
        try {
            employeeService.delete(id);
            response.sendRedirect("/employees");
        } catch (DataException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            request.setAttribute("message", e.getMessage());
            request.getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }
}
