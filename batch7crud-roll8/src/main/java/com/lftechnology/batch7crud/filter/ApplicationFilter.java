package com.lftechnology.batch7crud.filter;

import com.lftechnology.batch7crud.constants.AttributeConstants;
import com.lftechnology.batch7crud.constants.UrlConstants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Grishma Shrestha grishmashrestha@lftechnology.com on 1/28/16.
 */
public class ApplicationFilter implements Filter {

  @Override
  public void init(FilterConfig fConfig) throws ServletException {
//
  }

  @Override
  public void destroy() {
//
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    HttpServletRequest req = (HttpServletRequest) request;
    HttpSession session = req.getSession();
    HttpServletResponse res = (HttpServletResponse) response;
    String loginPath = req.getContextPath() + UrlConstants.USER_SIGN_IN_ROUTE;

    if (session.getAttribute(AttributeConstants.USER) == null && !loginPath.equals(req.getRequestURI())){
      res.sendRedirect(req.getContextPath() + UrlConstants.USER_SIGN_IN_ROUTE);
    }
    else {
      chain.doFilter(request, response);
    }
  }
}
