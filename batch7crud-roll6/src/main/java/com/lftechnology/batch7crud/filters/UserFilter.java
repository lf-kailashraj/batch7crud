package com.lftechnology.batch7crud.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lftechnology.batch7crud.constant.ApplicationConstant;

/**
 * Servlet Filter implementation class UserFilter
 */
@WebFilter("/UserFilter")
public class UserFilter implements Filter {

  private String loginUri;

  @Override
  public void init(FilterConfig fConfig) throws ServletException {
    loginUri = fConfig.getInitParameter("LoginURI");
  }

  @Override
  public void destroy() {
    loginUri = null;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;

    String uri = req.getRequestURI();
    HttpSession session = req.getSession();
    String user = (String) session.getAttribute("user");
    if (user == null && !loginUri.equals(uri)) {
      res.sendRedirect(ApplicationConstant.LOGIN);
    } else {
      chain.doFilter(request, response);

    }

  }

}
