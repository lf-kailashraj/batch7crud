package com.lftechnology.batch7crud.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lftechnology.batch7crud.constants.NormalConstants;
import com.lftechnology.batch7crud.constants.UrlConstants;
import com.lftechnology.batch7crud.validator.UrlValidator;

@WebServlet("/home/*")
public class HomeController extends CustomHttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (UrlValidator.isAuthenticationURL(request)) { // NOSONAR
            request.getRequestDispatcher(UrlConstants.HOME_PAGE).forward(request, response); // NOSONAR
        } else {
            throw new ServletException(NormalConstants.PAGE_NOT_FOUND); // NOSONAR
        }
    }
}
