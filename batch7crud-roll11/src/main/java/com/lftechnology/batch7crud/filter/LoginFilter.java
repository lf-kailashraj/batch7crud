package com.lftechnology.batch7crud.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by achyut on 2/2/16.
 */
@WebFilter("/UserFilter")
public class LoginFilter implements Filter {

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
    if (uri.contains("/css") || uri.contains("/images") ) {
      chain.doFilter(request, response);
    } else if (user == null && !loginUri.equals(uri)) {
      res.sendRedirect("/login");
    } else {
      chain.doFilter(request, response);
    }
  }

}
