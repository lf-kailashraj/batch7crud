package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.services.EmployeeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Grishma Shrestha grishmashrestha@lftechnology.com on 1/19/16.
 */
@WebServlet({ "/employees/*" })
public class EmployeesController extends CommonHttpServlet {
  private static final Logger LOGGER = Logger.getLogger("employeeLogger");

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String path = request.getPathInfo();
    if (path == null) {
      fetch(request, response);
    }
    else {
      String[] parts = path.split("/");
      if (parts.length == 0) {
        fetch(request, response);
      }
      else if (parts.length == 2 && "create".equals(parts[1])) {
        create(request, response);
      }
      else if (parts.length == 2) {
        view(request, response);
      }
      else if (parts.length == 3 && "edit".equals(parts[2])) {
        edit(request, response);
      }
      else {
        pageNotFound(request, response);
      }
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String path = request.getPathInfo();
    if (path == null) {
      request.getServletContext().getRequestDispatcher("/WEB-INF/views/employees/index.jsp").forward(request, response);
    }
    else {
      String[] parts = path.split("/");
      if ("create".equals(parts[1])) {
        createProcess(request, response);
      }
      else if ("edit".equals(parts[2])) {
        int id = Integer.parseInt(parts[1]);
        editProcess(request, response, id);
      }
      else if ("delete".equals(parts[2])) {
        int id = Integer.parseInt(parts[1]);
        deleteProcess(request, response, id);
      }
    }
  }

  private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getServletContext().getRequestDispatcher("/WEB-INF/views/employees/new.jsp").forward(request, response);
  }

  private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      int id = parameterValueAsInt(request, 2);
      EmployeeService employeeService = new EmployeeService();
      Employee employee = employeeService.fetchById(id);
      request.setAttribute("employee", employee);
      RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/employees/edit.jsp");
      dispatcher.forward(request, response);
    }
    catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      errorPage(request, response, e.getMessage());
    }

  }

  private void createProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    try {
      String name = request.getParameter("name");
      String address = request.getParameter("address");
      String designation = request.getParameter("designation");
      String phone = request.getParameter("phone");
      EmployeeService employeeService = new EmployeeService();

      Employee employee = new Employee();
      employee.setName(name);
      employee.setAddress(address);
      employee.setDesignation(designation);
      employee.setPhone(phone);

      employeeService.create(employee);
      response.sendRedirect("/employees");
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      errorPage(request, response, e.getMessage());
    }
  }

  private void editProcess(HttpServletRequest request, HttpServletResponse response, Integer id) throws IOException, ServletException {
    try{

      String name = request.getParameter("name");
      String address = request.getParameter("address");
      String designation = request.getParameter("designation");
      String phone = request.getParameter("phone");

      Employee employee = new Employee();
      employee.setName(name);
      employee.setAddress(address);
      employee.setDesignation(designation);
      employee.setPhone(phone);

      EmployeeService employeeService = new EmployeeService();
      employeeService.edit(employee, id);
      response.sendRedirect("/employees");
    }
    catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      errorPage(request, response, e.getMessage());
    }

  }

  private void deleteProcess(HttpServletRequest request, HttpServletResponse response, Integer id) throws IOException, ServletException {
    try {
      EmployeeService employeeService = new EmployeeService();
      employeeService.delete(id);
      response.sendRedirect("/employees");
    }
    catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      errorPage(request, response, e.getMessage());
    }
  }

  private void fetch(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    try {
      Integer pageLimit = 4;
      Integer pageNo = getCurrentPage(request);
      Integer offset = (pageNo-1)*pageLimit;
      EmployeeService employeeService = new EmployeeService();
      List<Employee> employeeList = employeeService.fetch(pageLimit, offset);
      Integer employeeCount = employeeService.count();
      request.setAttribute("employeeList", employeeList);
      request.setAttribute("employeeCount", employeeCount);
      request.setAttribute("pageNo", pageNo);
      if ((employeeCount%pageLimit) == 0) {
        request.setAttribute("lastPageNo", employeeCount / pageLimit);
      }
      else {
        request.setAttribute("lastPageNo", (employeeCount / pageLimit) + 1);
      }
      request.getServletContext().getRequestDispatcher("/WEB-INF/views/employees/index.jsp").forward(request, response);
    }
    catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      errorPage(request, response, e.getMessage());
    }
  }

  private void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      int id = parameterValueAsInt(request, 2);
      EmployeeService employeeService = new EmployeeService();
      Employee employee = employeeService.fetchById(id);
      if (employee != null) {
        request.setAttribute("employee", employee);
        request.getRequestDispatcher("/WEB-INF/views/employees/view.jsp").forward(request, response);
      }
      else {
        pageNotFound(request, response);
      }
    }
    catch (NumberFormatException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      pageNotFound(request, response);
    }
    catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      errorPage(request, response, e.getMessage());
    }
  }

  private int getCurrentPage(HttpServletRequest request) {
    try {
      if (request.getParameter("page") == null) {
        return 1;
      } else {
        return Integer.parseInt(request.getParameter("page"));
      }
    } catch(NumberFormatException e){
      return 1;
    }
  }
}
