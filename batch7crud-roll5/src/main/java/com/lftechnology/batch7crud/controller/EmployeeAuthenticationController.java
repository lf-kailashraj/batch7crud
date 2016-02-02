package com.lftechnology.batch7crud.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import com.lftechnology.batch7crud.constants.NormalConstants;
import com.lftechnology.batch7crud.constants.UrlConstants;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.exception.EncryptionException;
import com.lftechnology.batch7crud.model.Admin;
import com.lftechnology.batch7crud.services.AdminServiceImpl;
import com.lftechnology.batch7crud.util.MD5Encryption;
import com.lftechnology.batch7crud.validator.UrlValidator;

@WebServlet("/employeeAuthentication/*")
public class EmployeeAuthenticationController extends CustomHttpServlet {
    private static final long serialVersionUID = 1L;
    AdminServiceImpl adminServiceImpl = new AdminServiceImpl(); // NOSONAR
    Admin admin = new Admin();
    private static final Logger LOGGER = Logger.getLogger(EmployeeController.class.getName());
    private static final String USERNAME = "name";
    private static final String PASSWORD = "password";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        try {
            if (path != null && UrlValidator.isAuthenticationURL(request)) {
                String[] pathParts = parameterValues(request);
                if (pathParts.length == 3 && NormalConstants.LOGOUT.equals(pathParts[2])) {
                    logout(request, response);
                }
            }
        } catch (ServletException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new ServletException(NormalConstants.PAGE_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        try {
            if (path != null && UrlValidator.isAuthenticationURL(request)) {
                String[] pathParts = parameterValues(request);
                if (pathParts.length == 3 && NormalConstants.LOGIN.equals(pathParts[2])) {
                    login(request, response);
                }
            }
        } catch (NoSuchAlgorithmException | EncryptionException | ServletException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new ServletException(NormalConstants.PAGE_NOT_FOUND);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException, EncryptionException {
        try {
            String name = request.getParameter(USERNAME);
            String password = request.getParameter(PASSWORD);
            admin = adminServiceImpl.login(name, MD5Encryption.getMD5(password));
            if (admin != null) {
                HttpSession session = request.getSession();
                session.setAttribute(USERNAME, name);
                request.setAttribute("admin", admin);
                request.getRequestDispatcher(UrlConstants.HOME_PAGE).forward(request, response);
            } else {
                request.getRequestDispatcher(UrlConstants.LOGIN_PAGE).forward(request, response);
            }
        } catch (DataException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new ServletException(NormalConstants.INTERNAL_SERVER_ERROR);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            HttpSession session = request.getSession();
            session.invalidate();
            request.getRequestDispatcher(UrlConstants.LOGIN_PAGE).forward(request, response);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new ServletException(NormalConstants.INTERNAL_SERVER_ERROR);
        }
    }
}