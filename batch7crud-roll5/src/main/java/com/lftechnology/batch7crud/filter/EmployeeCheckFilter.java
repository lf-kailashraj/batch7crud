package com.lftechnology.batch7crud.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lftechnology.batch7crud.constants.UrlConstants;

public class EmployeeCheckFilter implements Filter {
    private String loginActionUri;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        loginActionUri = filterConfig.getInitParameter("loginActionURI");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String employeeName = (String) session.getAttribute("name");
        if (request.getRequestURI().endsWith(".css") || request.getRequestURI().endsWith(".png")) {
            chain.doFilter(request, response);
        } else if (employeeName == null && !request.getRequestURI().equals(request.getContextPath() + loginActionUri)) {
            request.getRequestDispatcher(UrlConstants.LOGIN_PAGE).forward(request, response);
        } else
            chain.doFilter(request, response);
    }

    @Override
    public void destroy() { // NOSONAR

    }
}
