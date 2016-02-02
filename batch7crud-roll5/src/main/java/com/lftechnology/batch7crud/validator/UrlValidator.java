package com.lftechnology.batch7crud.validator;

import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.lftechnology.batch7crud.constants.NormalConstants;

public class UrlValidator {
    static boolean isValid = false;

    private UrlValidator() {

    }

    public static boolean isCrudUrl(HttpServletRequest request) throws ServletException {
        String[] pathParts = urlPath(request);

        switch (pathParts.length) {
        case 2:
            isValid = true;
            break;

        case 3:
            if (NormalConstants.CREATE.equals(pathParts[2]) || EmployeeValidator.isInteger(pathParts[2])) {
                isValid = true;
            }
            break;

        case 4: // NOSONAR
            if (NormalConstants.EDIT.equals(pathParts[3]) || NormalConstants.DELETE_PROCESS.equals(pathParts[3])) {
                isValid = true;
            }
            break;

        default:
            throw new ServletException(NormalConstants.PAGE_NOT_FOUND);
        }
        return isValid;
    }

    public static boolean isAuthenticationURL(HttpServletRequest request) throws ServletException {
        String[] pathParts = urlPath(request);

        if (pathParts.length == 3 && NormalConstants.LOGIN.equals(pathParts[2]) || NormalConstants.LOGOUT.equals(pathParts[2])) {
            isValid = true;
        } else {
            throw new ServletException(NormalConstants.PAGE_NOT_FOUND);
        }
        return isValid;

    }

    private static String[] urlPath(HttpServletRequest request) {
        String urlPath = request.getRequestURI().substring(request.getContextPath().length());
        return urlPath.split(File.separator);
    }

}
