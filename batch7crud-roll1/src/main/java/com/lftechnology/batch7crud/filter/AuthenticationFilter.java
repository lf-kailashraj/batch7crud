package com.lftechnology.batch7crud.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.lftechnology.batch7crud.constant.AttributeConstant.*;
import static com.lftechnology.batch7crud.constant.URLConstant.*;

/**
 * Filter Class
 * <p>
 * Created by kiran on 1/28/16.
 */
@WebFilter(filterName = "AuthenticationFilter")
public class AuthenticationFilter implements Filter {

  @Override
  public void init(FilterConfig config) throws ServletException {
    //
  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) resp;
    HttpSession session = request.getSession();

    String user = (String) session.getAttribute(USER);
    String uri = request.getRequestURI();
    String loginURI = request.getContextPath() + LOGIN_PATH;

    if (uri.contains("/resources")) {
      chain.doFilter(req, resp);
    } else if (user == null && !loginURI.equals(uri)) {
      response.sendRedirect(loginURI); //NOSONAR
    } else {
      chain.doFilter(req, resp);
    }
  }

  @Override
  public void destroy() {
    //
  }
}
