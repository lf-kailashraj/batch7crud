package com.lftechnology.batch7crud.filter;

import static com.lftechnology.batch7crud.constant.UrlConstants.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by pratishshr on 1/26/16.
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
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    HttpSession session = request.getSession();
    String username = (String) session.getAttribute("username");

    if (username == null && !LOGIN_ACTION_URI.equals(request.getRequestURI())) {
      RequestDispatcher view = request.getRequestDispatcher(VIEW + "login.jsp");
      view.forward(request, response);
    } else {
      filterChain.doFilter(request, response);
    }
  }

  @Override
  public void destroy() {

  }
}
