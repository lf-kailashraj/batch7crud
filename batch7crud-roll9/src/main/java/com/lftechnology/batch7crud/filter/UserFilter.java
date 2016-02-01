package com.lftechnology.batch7crud.filter;

import com.lftechnology.batch7crud.constants.AppConstant;
import com.lftechnology.batch7crud.constants.PageConstant;
import com.lftechnology.batch7crud.constants.UrlConstant;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Created by sanjay on 1/29/16.
 */
public class UserFilter implements Filter {
  private String LOGIN_ACTION_URI;

  @Override
  public void init(FilterConfig fConfig) throws ServletException {
    LOGIN_ACTION_URI = fConfig.getInitParameter("loginActionURI");
  }

  @Override
  public void destroy() {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpSession session = req.getSession();
    String user = (String) session.getAttribute(AppConstant.USER);

    if (user == null && !(req.getContextPath()+LOGIN_ACTION_URI).equals(req.getRequestURI()) && !req.getRequestURI().contains(UrlConstant.STATIC)){
      RequestDispatcher rd = req.getRequestDispatcher(PageConstant.LOGIN_PAGE);
      rd.forward(request, response);
      return;
    }else{
      chain.doFilter(request, response);
    }

  }
}