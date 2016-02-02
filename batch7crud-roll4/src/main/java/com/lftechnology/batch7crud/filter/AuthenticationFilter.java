package com.lftechnology.batch7crud.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by pratishshr on 1/26/16.
 */
public class AuthenticationFilter implements Filter {
  private String loginUri;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    loginUri = filterConfig.getInitParameter("loginUri");
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
          throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;

    HttpSession session = request.getSession();
    String username = (String) session.getAttribute("username");

    if (username == null && !(request.getContextPath() + loginUri).equals(request.getRequestURI())
            && !request.getRequestURI().contains("/resources")) {
      response.sendRedirect(request.getContextPath() + "/auth/login");
    } else {
      filterChain.doFilter(request, response);
    }
  }

  @Override
  public void destroy() {
    loginUri = null;
  }
}
