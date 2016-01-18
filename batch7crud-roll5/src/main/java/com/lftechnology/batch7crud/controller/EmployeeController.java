package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.exception.DataException;
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
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getPathInfo();
		if (path != null) {
			System.out.println(path);
			String[] pathParts = path.split("/");

			if (pathParts[1].equals("create"))
				create(request, response);

		}

		else {
			fetchData(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		String path = request.getPathInfo();
		if (path != null) {
			System.out.println(path);
			String[] pathParts = path.split("/");

			if (pathParts[2].equals("createProcess")) {
				createProcess(request, response);
			}
		}

	}

	@SuppressWarnings("null")
	private void createProcess(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("sucess");
		try {
			Employee emp = new Employee();
			emp.setFirstName(request.getParameter("firstName"));
			emp.setLastName(request.getParameter("lastName"));
			emp.setDepartment(request.getParameter("department"));
			emp.setAddress(request.getParameter("address"));

			employeeService.create(emp);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private void create(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("creating");
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/createEmployee.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void fetchData(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			// System.out.println("laxman");
			List<Employee> employeeList = new ArrayList<Employee>();
			employeeList = employeeService.fetch();
			request.setAttribute("employeeList", employeeList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/employee.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
