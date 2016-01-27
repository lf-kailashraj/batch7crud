package com.lftechnology.batch7crud.filter;

import com.lftechnology.batch7crud.constant.PageConstant;
import com.lftechnology.batch7crud.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author binodnme
 * Created on 1/27/16
 */

public class AuthenticationFilter implements Filter{

  private static String loginActionUri;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    loginActionUri =  filterConfig.getInitParameter("loginActionURI");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpSession session = req.getSession();
    User user = (User) session.getAttribute("user");

    if (user == null && !loginActionUri.equals(req.getRequestURI())){
      RequestDispatcher rd = req.getRequestDispatcher(PageConstant.LOGIN);
      rd.forward(request, response);
      return;
    }

    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {

  }
}
