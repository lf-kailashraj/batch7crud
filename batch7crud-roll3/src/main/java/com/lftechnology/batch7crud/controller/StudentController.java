package com.lftechnology.batch7crud.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.service.StudentService;

@WebServlet("/student/*")
public class StudentController extends HttpServlet {
	private StudentService studentService = new StudentService();
	private Logger logger = Logger.getLogger("StudentController");

	private static final String MESSAGE = "message";
	private static final String ERROR_PAGE = "/WEB-INF/views/error.jsp";
	private static final String LIST_PAGE = "/batch7crud-roll3/student";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		try {
			if (pathInfo == null) {
				list(request, response, 1);
			} else {
				String[] pathParts = pathInfo.split("/");

				if ("create".equals(pathParts[1])) {
					create(request, response);
				} else if (pathParts[1] != null && pathParts.length == 2) {
					int page;
					page = Integer.parseInt(pathParts[1]);

					list(request, response, page);
				} else if (pathParts[1] != null && "edit".equals(pathParts[2])) {
					edit(request, response, Integer.parseInt(pathParts[1]));

				} else if (pathParts[1] != null && "delete".equals(pathParts[2])) {
					deleteProcess(request, response, Integer.parseInt(pathParts[1]));

				} else {
					response.sendRedirect(LIST_PAGE);
				}
			}
		} catch (NumberFormatException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		String[] pathParts = pathInfo.split("/");

		try {
			if ("create".equals(pathParts[1])) {
				createProcess(request, response);
			} else if ("edit".equals(pathParts[2])) {
				editProcess(request, response, Integer.parseInt(pathParts[1]));
			} else if ("delete".equals(pathParts[2])) {
				deleteProcess(request, response, Integer.parseInt(pathParts[1]));
			} else {
				list(request, response, 1);
			}

		} catch (NumberFormatException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response, int page)
			throws ServletException, IOException {
		try {
			int pageSize = 3;
			List<Student> stdList = studentService.fetch(page, pageSize);
			int count = studentService.fetchTotal();

			request.setAttribute("stdList", stdList);
			request.setAttribute("pageSize", pageSize);

			request.setAttribute("count", count);
			request.setAttribute("pageNum", page);
			request.getRequestDispatcher("/WEB-INF/views/student/list.jsp").forward(request, response);
		} catch (DataException e) {
			logger.log(Level.SEVERE, e.getMessage());

			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			request.setAttribute(MESSAGE, e.getMessage());
			request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
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
		try {
			student.setRoll(Integer.parseInt(roll));
		} catch (NumberFormatException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}

		student.setName(name);
		try {
			studentService.insert(student);
			response.sendRedirect(LIST_PAGE);
		} catch (DataException e) {
			logger.log(Level.SEVERE, e.getMessage());

			request.setAttribute(MESSAGE, e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
		}

	}

	private void edit(HttpServletRequest request, HttpServletResponse response, int id)
			throws ServletException, IOException {
		Student student;
		try {
			student = studentService.fetchStudentById(id);
			request.setAttribute("student", student);
			request.getRequestDispatcher("/WEB-INF/views/student/edit.jsp").forward(request, response);
		} catch (DataException e) {
			logger.log(Level.SEVERE, e.getMessage());

			request.setAttribute(MESSAGE, e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
		}

	}

	private void editProcess(HttpServletRequest request, HttpServletResponse response, int id)
			throws ServletException, IOException {
		try {
			String roll = request.getParameter("roll");
			String name = request.getParameter("name");
			Student student = new Student();
			student.setRoll(Integer.parseInt(roll));
			student.setName(name);

			studentService.edit(student, id);

			response.sendRedirect(LIST_PAGE);
		} catch (NumberFormatException e) {
			logger.log(Level.SEVERE, e.getMessage());
		} catch (DataException e) {
			logger.log(Level.SEVERE, e.getMessage());

			request.setAttribute(MESSAGE, e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
		}
	}

	private void deleteProcess(HttpServletRequest request, HttpServletResponse response, int id)
			throws ServletException, IOException {
		try {
			studentService.delete(id);
			response.sendRedirect(LIST_PAGE);
		} catch (DataException e) {
			logger.log(Level.SEVERE, e.getMessage());

			request.setAttribute(MESSAGE, e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
		}
	}
}
