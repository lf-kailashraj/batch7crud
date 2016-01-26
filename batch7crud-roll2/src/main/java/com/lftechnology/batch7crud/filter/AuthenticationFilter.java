package com.lftechnology.batch7crud.filter;

import com.lftechnology.batch7crud.constants.AttributeConstants;
import com.lftechnology.batch7crud.constants.UrlConstants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Romit Amgai <romitamgai@lftechnology.com> on 1/26/16.
 */

public class AuthenticationFilter implements Filter {
  private String LOGIN_ACTION_URI;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    LOGIN_ACTION_URI = filterConfig.getInitParameter("loginActionURI");
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
          throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) servletRequest;
    HttpServletResponse res = (HttpServletResponse) servletResponse;
    HttpSession session = req.getSession();
    String userSession = (String) session.getAttribute(AttributeConstants.USER);
    if (userSession == null && !LOGIN_ACTION_URI.equals(req.getRequestURI())) {
      res.sendRedirect(req.getContextPath() + UrlConstants.LOGIN_ROUTE);
    } else {
      filterChain.doFilter(servletRequest, servletResponse);
    }
  }

  @Override
  public void destroy() {

  }
}
