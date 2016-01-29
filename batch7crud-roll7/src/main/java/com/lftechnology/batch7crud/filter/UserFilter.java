package com.lftechnology.batch7crud.filter;

import com.lftechnology.batch7crud.controller.CommonConstants;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by leapfrog on 1/27/16.
 */
public class UserFilter implements Filter {
  private String LOGIN_ACTION_URI;

  @Override
  public void init(FilterConfig fConfig) throws ServletException {
    LOGIN_ACTION_URI = fConfig.getInitParameter("loginActionURI");
  }

  @Override
  public void destroy() {
    //
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpSession session = req.getSession();
    String user = (String) session.getAttribute("user");
    if (user == null && !(req.getContextPath()+LOGIN_ACTION_URI).equals(req.getRequestURI())){
      RequestDispatcher rd = req.getRequestDispatcher(CommonConstants.LOG_IN_VIEW);
      rd.forward(request, response);
      return;
    }
    chain.doFilter(request, response);
  }
}
