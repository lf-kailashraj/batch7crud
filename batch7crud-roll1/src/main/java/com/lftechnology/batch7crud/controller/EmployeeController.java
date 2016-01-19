package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.service.EmployeeService;
import com.lftechnology.batch7crud.util.TypeCaster;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmployeeController
 */
@WebServlet({ "/employees/*" })
public class EmployeeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	EmployeeService employeeService = new EmployeeService();
	private static Logger logger = Logger.getLogger(EmployeeController.class.getName());
	private static final String CREATE = "create";
	private static final String DELETE = "delete";
	private static final String EDIT = "edit";


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeController() {

		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(
					HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {

		String requestInfo = request.getPathInfo();

		if (requestInfo == null) {
			list(request, response, 1);
		}
		else {

			String[] pathParts = requestInfo.split("/");

			if (CREATE.equals(pathParts[1])) {
				create(request, response);
			}
			else if (EDIT.equals(pathParts[2])) {
				int id = TypeCaster.toInt(pathParts[1]);
				edit(request, response, id);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(
					HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {

		String requestInfo = request.getPathInfo();

		String[] pathParts = requestInfo.split("/");

		if (CREATE.equals(pathParts[1])) {
			createProcess(request, response);
		}
		else if (EDIT.equals(pathParts[2])) {
			int id = TypeCaster.toInt(pathParts[1]);
			editProcess(request, response, id);
		}
		else if (DELETE.equals(pathParts[2])) {
			int id = TypeCaster.toInt(pathParts[1]);
			deleteProcess(request, response, id);
		}
	}

	private void list(
					HttpServletRequest request, HttpServletResponse response,
					int page)
					throws ServletException, IOException {

		try {
			List<Employee> employees = employeeService.fetch();
			request.setAttribute("employees", employees);
			request.getServletContext().getRequestDispatcher(
							"/WEB-INF/views/employee/list.jsp").forward(
							request, response);
		}
		catch (DataException e) {

			logger.log(Level.SEVERE, e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			request.setAttribute("message", e.getMessage());
			request.getServletContext().getRequestDispatcher(
							"/WEB-INF/views/error.jsp").forward(
							request, response);
		}
	}

	private void create(
					HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {

		request.getServletContext().getRequestDispatcher(
						"/WEB-INF/views/employee/create.jsp").forward(
						request, response);
	}

	private void createProcess(
					HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {

		int id = TypeCaster.toInt(request.getParameter("id"));
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String fullName = request.getParameter("fullName");
		String department = request.getParameter("department");
		String address = request.getParameter("address");
		Employee employee = new Employee(
						id, userName, password, fullName, department, address);

		try {
			employeeService.create(employee);
			response.sendRedirect(request.getContextPath() + "/employees");
		}
		catch (DataException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			request.setAttribute("message", e.getMessage());
			request.getServletContext().getRequestDispatcher(
							"/WEB-INF/views/error.jsp").forward(
							request, response);
		}
	}

	private void edit(
					HttpServletRequest request, HttpServletResponse response,
					int id)
					throws ServletException, IOException {

		try {
			Employee employee = employeeService.fetchById(id);
			request.setAttribute("employee", employee);
			request.getServletContext().getRequestDispatcher(
							"/WEB-INF/views/employee/edit.jsp").forward(
							request, response);
		}
		catch (DataException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			request.setAttribute("message", e.getMessage());
			request.getServletContext().getRequestDispatcher(
							"/WEB-INF/views/error.jsp").forward(
							request, response);
		}
	}

	private void editProcess(
					HttpServletRequest request, HttpServletResponse response,
					int id)
					throws ServletException, IOException {

		int empId = TypeCaster.toInt(request.getParameter("id"));
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String fullName = request.getParameter("fullName");
		String department = request.getParameter("department");
		String address = request.getParameter("address");
		Employee employee = new Employee(
						empId, userName, password, fullName, department,
						address);

		try {
			employeeService.update(employee);
			response.sendRedirect("/employees");
		}
		catch (DataException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			request.setAttribute("message", e.getMessage());
			request.getServletContext().getRequestDispatcher(
							"/WEB-INF/views/error.jsp").forward(
							request, response);
		}
	}

	private void deleteProcess(
					HttpServletRequest request, HttpServletResponse response,
					int id)
					throws ServletException, IOException {

		try {
			employeeService.delete(id);
			response.sendRedirect(request.getContextPath() + "/employees");
		}
		catch (DataException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			request.setAttribute("message", e.getMessage());
			request.getServletContext().getRequestDispatcher(
							"/WEB-INF/views/error.jsp").forward(
							request, response);
		}
	}
}
