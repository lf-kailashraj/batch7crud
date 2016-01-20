package com.lftechnology.batch7crud.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.service.StudentService;

@WebServlet("/students/*")
public class StudentController extends CustomHttpServlet {
	private static final String LIST_PAGE = "/students";
	private static StudentService studentService = new StudentService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] parameters = parameterValues(request);
		try {
			if (parameters.length == 2) {
				list(request, response);
			} else if (parameters.length == 3 && "create".equals(parameters[2])) {
				create(request, response);
			} else if (parameters.length == 4 && "edit".equals(parameters[3])) {
				edit(request, response);
			} else if (parameters.length == 3) {
				show(request, response);
			} else {
				show404(request, response);
			}
		} catch (DataException | IOException | ServletException e) {
			show500(request, response, e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
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
		} catch (DataException | IOException | ServletException e) {
			show500(request, response, e);
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response)
			throws DataException, ServletException, IOException {
		int pageSize = 3;
		int page = getPageNumber(request);
		List<Student> stdList = studentService.fetch(page, pageSize);
		int count = studentService.fetchTotal();

		request.setAttribute("stdList", stdList);
		request.setAttribute("pageSize", pageSize);

		request.setAttribute("count", count);
		request.setAttribute("pageNum", page);
		request.getRequestDispatcher("/WEB-INF/views/student/list.jsp").forward(request, response);
	}

	private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DataException {
		int id = parameterValueAsInt(request, 2);
		Student student = studentService.fetchStudentById(id);
		request.setAttribute("student", student);
		request.getRequestDispatcher("/WEB-INF/views/student/show.jsp").forward(request, response);
	}

	private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/student/create.jsp").forward(request, response);
	}

	private void createProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DataException {
		String roll = request.getParameter("roll");
		String name = request.getParameter("name");
		try {

			Student student = new Student();
			student.setRoll(Integer.parseInt(roll));
			student.setName(name);
			response.sendRedirect(request.getContextPath() + LIST_PAGE);
		} catch (NumberFormatException e) {
			request.setAttribute("roll", roll);
			request.setAttribute("name", name);

			request.setAttribute("message", "Fill valid Roll");
			request.getRequestDispatcher("/WEB-INF/views/student/create.jsp").forward(request, response);
		}
	}

	private void edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DataException {
		try {
			int id = parameterValueAsInt(request, 2);
			Student student = studentService.fetchStudentById(id);
			request.setAttribute("student", student);
			request.getRequestDispatcher("/WEB-INF/views/student/edit.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			show404(request, response);
		}

	}

	private void editProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DataException {
		String roll = request.getParameter("roll");
		String name = request.getParameter("name");

		try {
			int id = parameterValueAsInt(request, 2);
			Student student = new Student();
			student.setRoll(Integer.parseInt(roll));
			student.setName(name);
			studentService.edit(student, id);

			response.sendRedirect(request.getContextPath() + LIST_PAGE);
		} catch (NumberFormatException e) {
			request.setAttribute("roll", roll);
			request.setAttribute("name", name);
			request.setAttribute("message", "Fill valid Roll");

			request.getRequestDispatcher("/WEB-INF/views/student/edit.jsp").forward(request, response);
		}
	}

	private void deleteProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DataException {
		int id = parameterValueAsInt(request, 2);
		studentService.delete(id);

		response.sendRedirect(request.getContextPath() + LIST_PAGE);
	}
}
