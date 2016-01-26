package com.lftechnology.batch7crud.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lftechnology.batch7crud.constant.CommonConstant;
import com.lftechnology.batch7crud.entity.User;

public class AuthenticationFilter implements javax.servlet.Filter {
  private String LOGIN_ACTION_URI;

  public void init(FilterConfig fConfig) throws ServletException {
    LOGIN_ACTION_URI = fConfig.getInitParameter("loginActionURI");
  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;
    HttpSession session = req.getSession();

    User user = (User) session.getAttribute("user");

    if (user == null && !LOGIN_ACTION_URI.equals(req.getRequestURI())) {
      RequestDispatcher rd = req.getRequestDispatcher(CommonConstant.LOGIN_PAGE);
      rd.forward(req, resp);
      return;
    }

    chain.doFilter(request, response);
  }

  /*
   * private boolean isAuth(HttpServletRequest request, HttpServletResponse
   * response) throws ServletException, IOException {
   * response.sendRedirect(CommonConstant.LOGIN_PAGE); return false; }
   */

  public void destroy() {
  }
}