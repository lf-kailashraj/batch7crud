package com.lftechnology.batch7crud.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lftechnology.batch7crud.constant.CommonConstant;

public class AuthenticationFilter implements javax.servlet.Filter {
  private String loginActionURI;

  @Override
  public void init(FilterConfig fConfig) throws ServletException {
    loginActionURI = fConfig.getInitParameter("loginActionURI");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;
    HttpSession session = req.getSession();

    String user = (String) session.getAttribute("user");

    if (user == null && !loginActionURI.equals(req.getRequestURI())) {
      RequestDispatcher rd = req.getRequestDispatcher(CommonConstant.LOGIN_PAGE);
      rd.forward(req, resp);
      return;
    }
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {
    loginActionURI = null;
  }
}