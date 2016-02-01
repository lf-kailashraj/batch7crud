package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constants.PageConstants;
import com.lftechnology.batch7crud.constants.ParameterConstants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by sagarmatha on 1/28/16.
 */
public abstract class CommonHTTPRequestHandler extends HttpServlet {
    protected void show404(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        RequestDispatcher view = request.getRequestDispatcher(PageConstants.ERROR_PAGE);
        view.forward(request, response);
    }

    protected void show500(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        RequestDispatcher view = request.getRequestDispatcher(PageConstants.ERROR_PAGE);
        view.forward(request, response);
    }

    public int pageNumber(HttpServletRequest request) {
        if (request.getParameter(ParameterConstants.PAGE) != null) {
            return Integer.parseInt(request.getParameter(ParameterConstants.PAGE));
        } else {
            return 1;
        }
    }

    public String[] parameterValues(HttpServletRequest request) {
        String urlPath = request.getRequestURI().substring(request.getContextPath().length());
        return urlPath.split(File.separator);
    }

    public int parameterValueAsInt(HttpServletRequest request, int index) {
        String[] paths = parameterValues(request);
        return Integer.parseInt(paths[index]);
    }
}
