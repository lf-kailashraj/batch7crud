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

	private void create(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("creating");

	}

	private void fetchData(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
//			System.out.println("laxman");
			List<Employee> employeeList = new ArrayList<Employee>();
			EmployeeService employeeService = new EmployeeService();
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
