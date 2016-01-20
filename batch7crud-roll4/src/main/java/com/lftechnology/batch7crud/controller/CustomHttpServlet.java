package com.lftechnology.batch7crud.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by pratishshr on 1/19/16.
 */
public class CustomHttpServlet extends HttpServlet {

    private static final String ERROR_PAGE = "/WEB-INF/views/error.jsp";
    private static final String MESSAGE = "message";

    protected void show404(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        request.setAttribute(MESSAGE, "Page Not Found");

        RequestDispatcher view = request.getRequestDispatcher(ERROR_PAGE);
        view.forward(request, response);
    }

    protected void show500(HttpServletRequest request, HttpServletResponse response, Throwable e) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        request.setAttribute(MESSAGE, e.getMessage());

        RequestDispatcher view = request.getRequestDispatcher(ERROR_PAGE);
        view.forward(request, response);
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

    public int parameterValueAsInt(HttpServletRequest request, int index) throws NumberFormatException {
        String[] paths = parameterValues(request);
        return Integer.parseInt(paths[index]);
    }

}
