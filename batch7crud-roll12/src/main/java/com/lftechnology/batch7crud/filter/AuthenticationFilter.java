package com.lftechnology.batch7crud.filter;

import com.lftechnology.batch7crud.constants.PageConstants;
import com.lftechnology.batch7crud.constants.ParameterConstants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by sagarmatha on 2/2/16.
 */
@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

    public void init(FilterConfig fConfig) throws ServletException {
        //initialization
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String userSession = (String) session.getAttribute(ParameterConstants.USER);
        String loginPath = req.getContextPath() + "/login";
        String uri = req.getRequestURI();
        if(uri.startsWith("/static")){
            chain.doFilter(request, response);
        }else if (userSession == null && !loginPath.equals(req.getRequestURI())) {
            res.sendRedirect(loginPath);
        } else
            chain.doFilter(request, response);
    }

    public void destroy() {
        //destruction
    }
}