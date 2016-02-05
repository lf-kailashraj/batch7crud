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
  private static final String USER = "user";
  private static String loginActionUri;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    loginActionUri =  filterConfig.getInitParameter("loginActionURI");    //NOSONAR
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;
    HttpSession session = req.getSession();
    User user = (User) session.getAttribute(USER);
    String uri = ((HttpServletRequest) request).getRequestURI();

    if(uri.startsWith("/static")){
      chain.doFilter(req, resp);
    }else if (user == null && !loginActionUri.equals(req.getRequestURI())){
      resp = (HttpServletResponse) response;
      resp.sendRedirect(PageConstant.LOGIN_URL);
      return;
    }

    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {   //NOSONAR

  }
}
