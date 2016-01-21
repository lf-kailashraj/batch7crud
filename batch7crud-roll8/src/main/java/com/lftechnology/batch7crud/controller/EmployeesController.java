package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.services.EmployeeService;
import com.lftechnology.batch7crud.util.TypeCaster;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
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
public class EmployeesController extends HttpServlet{
  private static final Logger LOGGER = Logger.getLogger("employeeLogger");
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String path = request.getPathInfo();
    if (path == null) {
      int pageNo = getCurrentPage(request);
      fetch(request, response, pageNo);
    }
    else {
      String[] parts = path.split("/");
      if (parts[1].equals("create")) {
        create(request, response);
      }
      else if (parts.length == 2) {
        Integer id = TypeCaster.toInt(parts[1]);
        view(request, response, id);
      }
      else if (parts[2].equals("edit")) {
        int id = Integer.parseInt(parts[1]);
        edit(request, response, id);
      }
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String path = request.getPathInfo();
    if (path == null) {
      request.getServletContext().getRequestDispatcher("/WEB-INF/views/employees/index.jsp").forward(request, response);
    }
    else {
      String[] parts = path.split("/");
      if (parts[1].equals("createProcess")) {
        createProcess(request, response);
      }
      else if (parts[2].equals("editProcess")) {
        int id = Integer.parseInt(parts[1]);
        editProcess(request, response, id);
      }
      else if (parts[2].equals("deleteProcess")) {
        int id = Integer.parseInt(parts[1]);
        deleteProcess(request, response, id);
      }
    }
  }

  private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getServletContext().getRequestDispatcher("/WEB-INF/views/employees/new.jsp").forward(request, response);
  }

  private void edit(HttpServletRequest request, HttpServletResponse response, Integer id) throws ServletException, IOException {
    try{
      EmployeeService employeeService = new EmployeeService();
      Employee employee = employeeService.fetchById(id);
      request.setAttribute("employee", employee);
      RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/employees/edit.jsp");
      dispatcher.forward(request, response);
    }
    catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      //needs to be handled
    }

  }

  private void createProcess(HttpServletRequest request, HttpServletResponse response) {
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
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  private void editProcess(HttpServletRequest request, HttpServletResponse response, Integer id) {
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
      //needs to be handled
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }

  }

  private void deleteProcess(HttpServletRequest request, HttpServletResponse response, Integer id) {
    try {
      EmployeeService employeeService = new EmployeeService();
      employeeService.delete(id);
      response.sendRedirect("/employees");
    }
    catch (IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
    catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  private void fetch(HttpServletRequest request, HttpServletResponse response, Integer pageNo) throws IOException {
    Integer pageLimit = 4;
    Integer offset = (pageNo-1)*pageLimit;
    try {
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
        request.setAttribute("lastPageNo", (employeeCount/pageLimit)+1);
      }

      request.getServletContext().getRequestDispatcher("/WEB-INF/views/employees/index.jsp").forward(request, response);
    }
    catch (Exception e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  private void view(HttpServletRequest request, HttpServletResponse response, Integer id) {
    try {
      EmployeeService employeeService = new EmployeeService();
      Employee employee = employeeService.fetchById(id);
      if (employee != null) {
        request.setAttribute("employee", employee);
        request.getRequestDispatcher("/WEB-INF/views/employees/view.jsp").forward(request, response);
      }
      else {
        System.out.println("User not found");
        // needs to be redirected
      }

    } catch (NumberFormatException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    } catch (ServletException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
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
