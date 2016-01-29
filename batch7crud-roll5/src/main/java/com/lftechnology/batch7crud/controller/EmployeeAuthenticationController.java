package com.lftechnology.batch7crud.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import com.lftechnology.batch7crud.constants.NormalConstants;
import com.lftechnology.batch7crud.constants.UrlConstants;
import com.lftechnology.batch7crud.services.AdminServiceImpl;
import com.lftechnology.batch7crud.util.MD5Encryption;

@WebServlet("/employeeAuthentication/*")
public class EmployeeAuthenticationController extends CustomHttpServlet {
    private static final long serialVersionUID = 1L;
    AdminServiceImpl adminServiceImpl = new AdminServiceImpl();
    private static final Logger LOGGER = Logger.getLogger(EmployeeController.class.getName());
    private static final String USERNAME = "name";
    private static final String PASSWORD = "password";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path != null) {
            String[] pathParts = parameterValues(request);
            if (pathParts.length == 3 && NormalConstants.LOGOUT.equals(pathParts[2])) {
                logout(request, response);
            }
        } else {
            show404(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path != null) {
            String[] pathParts = parameterValues(request);
            if (pathParts.length == 3 && NormalConstants.LOGIN.equals(pathParts[2])) {
                login(request, response);
            }
        } else {
            show404(request, response);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
        try {
            String name = request.getParameter(USERNAME);
            String password = request.getParameter(PASSWORD);
            if (adminServiceImpl.login(name, MD5Encryption.getMD5(password)) != null) {
                HttpSession session = request.getSession();
                session.setAttribute(USERNAME, name);
                response.sendRedirect(request.getContextPath() + "/employee");
            } else {
                request.getRequestDispatcher(UrlConstants.LOGIN_PAGE).forward(request, response);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            show500(request, response, e);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect(request.getContextPath());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            show500(request, response, e);
        }

    }
}