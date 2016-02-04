package com.lftechnology.batch7crud.controller;

import java.io.File;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import com.lftechnology.batch7crud.constants.NormalConstants;

@SuppressWarnings("serial")
public abstract class CustomHttpServlet extends HttpServlet {
    public int getPageNumber(HttpServletRequest request) {
        try {
            if (request.getParameter(NormalConstants.PAGE) != null) {
                return Integer.parseInt(request.getParameter(NormalConstants.PAGE));
            } else {
                return 1;
            }
        } catch (NumberFormatException e) {
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

    public String getAction(HttpServletRequest request) {
        String[] pathParts = parameterValues(request);
        String action = "";
        if (pathParts.length == 2) {
            action = NormalConstants.LIST;
        } else if (pathParts.length == 3 && NormalConstants.CREATE.equals(pathParts[2])) {
            action = NormalConstants.CREATE;
        } else if (pathParts.length == 4 && NormalConstants.EDIT.equals(pathParts[3])) {
            action = NormalConstants.EDIT;
        } else if (pathParts.length == 4 && NormalConstants.DELETE_PROCESS.equals(pathParts[3])) {
            action = NormalConstants.DELETE_PROCESS;
        }else if (pathParts.length == 3 && NormalConstants.CREATE_USING_AJAX.equals(pathParts[2])) {
            action = NormalConstants.CREATE_USING_AJAX;
        }
        return action;
    }

}
