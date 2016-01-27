package com.lftechnology.batch7crud.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import com.lftechnology.batch7crud.constants.UrlConstants;
import com.lftechnology.batch7crud.services.AdminServiceImpl;

@WebServlet("/employeeAuthentication/*")
public class EmployeeAuthenticationController extends CustomHttpServlet {
    private static final long serialVersionUID = 1L;
    AdminServiceImpl adminServiceImpl = new AdminServiceImpl();
    private static final Logger LOGGER = Logger.getLogger(EmployeeController.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        try {
            if (path != null) {
                String[] pathParts = path.split("/");
                if (pathParts.length == 2 && "logout".equals(pathParts[1])) {
                    HttpSession session = request.getSession();
                    session.invalidate();
                    response.sendRedirect(request.getContextPath());
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            show500(request, response, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        try {
            if (adminServiceImpl.login(name, password) != null) {
                HttpSession session = request.getSession();
                session.setAttribute("employeeName", name);
                response.sendRedirect(request.getContextPath() + "/employee");
            } else {
                request.getRequestDispatcher(UrlConstants.LOGIN_PAGE).forward(request, response);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            show500(request, response, e);
        }
    }
}
