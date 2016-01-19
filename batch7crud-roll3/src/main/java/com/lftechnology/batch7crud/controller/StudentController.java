package com.lftechnology.batch7crud.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.service.StudentService;

/**
 * Servlet implementation class StudentController
 */
@WebServlet("/student/*")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pathInfo = request.getPathInfo();
		if (pathInfo == null) {
			list(request, response, 1);
		} else {
			String[] pathParts = pathInfo.split("/");

			if (pathParts[1].equals("create")) {
				create(request, response);
			} else if (pathParts[1] != null && pathParts.length == 2) {
				int page = 1;
				if (pathParts[1] != null) {
					page = Integer.parseInt(pathParts[1]);
				}
				list(request, response, page);
			} else if (pathParts[1] != null && pathParts[2].equals("edit")) {
				edit(request, response, Integer.parseInt(pathParts[1]));

			} else if (pathParts[1] != null && pathParts[2].equals("delete")) {
				deleteProcess(request, response, Integer.parseInt(pathParts[1]));

			} else {
				response.sendRedirect("/batch7crud-roll3/student");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		String[] pathParts = pathInfo.split("/");
		
		try {
			if (pathParts[1].equals("create")) {
				createProcess(request, response);
			} else if (pathParts[2].equals("edit")) {
				editProcess(request, response, Integer.parseInt(pathParts[1]));
			} else if (pathParts[2].equals("delete")) {
				deleteProcess(request, response, Integer.parseInt(pathParts[1]));
			} else {
				list(request, response, 1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response, int page)
			throws ServletException, IOException {
		try {
			int pageSize = 3;
			StudentService stdService = new StudentService();
			List<Student> stdList = stdService.fetch(page, pageSize);
			int count = stdService.fetchTotal();
			
			request.setAttribute("stdList", stdList);
			request.setAttribute("pageSize", pageSize);

			request.setAttribute("count", count);
			request.setAttribute("pageNum", page);
			request.getRequestDispatcher("/WEB-INF/views/student/list.jsp").forward(request, response);
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
		}
	}

	private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/student/create.jsp").forward(request, response);
	}

	private void createProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String roll = request.getParameter("roll");
		String name = request.getParameter("name");

		Student student = new Student();
		student.setRoll(Integer.parseInt(roll));
		student.setName(name);

		StudentService stdService = new StudentService();
		try {
			stdService.insert(student);
			response.sendRedirect("/batch7crud-roll3/student");
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
		}

	}

	private void edit(HttpServletRequest request, HttpServletResponse response, int id)
			throws ServletException, IOException {
		StudentService stdService = new StudentService();
		Student student;
		try {
			student = stdService.fetchStudentById(id);
			request.setAttribute("student", student);
			request.getRequestDispatcher("/WEB-INF/views/student/edit.jsp").forward(request, response);
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
		}

	}

	private void editProcess(HttpServletRequest request, HttpServletResponse response, int id)
			throws ServletException, IOException {
		try {
			StudentService stdService = new StudentService();

			String roll = request.getParameter("roll");
			String name = request.getParameter("name");
			Student student = new Student();
			student.setRoll(Integer.parseInt(roll));
			student.setName(name);

			stdService.edit(student, id);

			response.sendRedirect("/batch7crud-roll3/student");
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
		}
	}

	private void deleteProcess(HttpServletRequest request, HttpServletResponse response, int id)
			throws ServletException, IOException {
		try {
			StudentService stdService = new StudentService();
			stdService.delete(id);
			response.sendRedirect("/batch7crud-roll3/student");
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
		}
	}
}
