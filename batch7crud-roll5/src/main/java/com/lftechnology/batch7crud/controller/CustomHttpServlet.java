package com.lftechnology.batch7crud.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public abstract class CustomHttpServlet extends HttpServlet {
    private static final String ERROR_PAGE = "/WEB-INF/views/error.jsp";
    private static final String MESSAGE = "message";
    private static final Logger LOGGER = Logger.getLogger("CustomHttpServletLog");

    protected void show404(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            request.setAttribute(MESSAGE, "Page Not Found");
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        } catch (ServletException | IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }

    }

    protected void show500(HttpServletRequest request, HttpServletResponse response, Throwable e) {
        try {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            request.setAttribute(MESSAGE, e.getMessage());

            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        } catch (ServletException | IOException exc) {
            LOGGER.log(Level.SEVERE, exc.getMessage(), exc);
        }
    }

    public int pageNumber(HttpServletRequest request) {
        try {
            if (request.getParameter("page") != null) {
                return Integer.parseInt(request.getParameter("page"));
            } else {
                return 1;
            }
        } catch (NumberFormatException e) {
            return 1;
        }
    }

    public String[] parameterValues(HttpServletRequest request) {
        String urlPath = request.getRequestURI().substring(request.getContextPath().length());
        return urlPath.split("/");
    }

    public int parameterValueAsInt(HttpServletRequest request, int index) {
        String[] paths = parameterValues(request);
        return Integer.parseInt(paths[index]);
    }

}
