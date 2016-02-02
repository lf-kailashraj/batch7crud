package com.lftechnology.batch7crud.filter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import com.lftechnology.batch7crud.constants.NormalConstants;

public class ExceptionFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(ExceptionFilter.class.getName());

    @Override
    public void destroy() { // NOSONAR

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        try {
            chain.doFilter(request, response);
        } catch (ServletException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            System.out.println(e.getMessage());
            if (NormalConstants.PAGE_NOT_FOUND.equals(e.getMessage())) {
                request.setAttribute(NormalConstants.MESSAGE, NormalConstants.PAGE_NOT_FOUND);
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_NOT_FOUND);
            } else {
                request.setAttribute(NormalConstants.MESSAGE, NormalConstants.INTERNAL_SERVER_ERROR);
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException { // NOSONAR
    }

}
