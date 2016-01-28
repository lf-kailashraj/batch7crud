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
    private String LOGIN_ACTION_URI;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGIN_ACTION_URI = filterConfig.getInitParameter("loginActionURI");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String employeeName = (String) session.getAttribute("name");
        System.out.println(request.getRequestURI());
        System.out.println(request.getContextPath() + LOGIN_ACTION_URI);
        if (employeeName == null && !request.getRequestURI().equals(request.getContextPath() + LOGIN_ACTION_URI)) {
            request.getRequestDispatcher(UrlConstants.LOGIN_PAGE).forward(request, response);
        } else
            chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        
    }
}
