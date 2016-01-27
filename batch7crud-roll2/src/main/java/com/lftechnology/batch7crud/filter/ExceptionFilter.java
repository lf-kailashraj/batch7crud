package com.lftechnology.batch7crud.filter;

import com.lftechnology.batch7crud.constants.UrlConstants;
import com.lftechnology.batch7crud.exception.ExceptionHandler;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Romit Amgai <romitamgai@lftechnology.com> on 1/28/16.
 */
public class ExceptionFilter implements Filter {
  ExceptionHandler exceptionHandler;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    //initializations can be done here
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
    try {
      filterChain.doFilter(request, response);
    } catch (Exception ex) {
      // call ErrorHandler and dispatch to error jsp
      String errorMessage = exceptionHandler.handle(ex);
      request.setAttribute("message", errorMessage);
      request.getRequestDispatcher(UrlConstants.ERROR_PAGE).forward(request, response);
    }
  }

  @Override
  public void destroy() {
    //Filter can be destroyed here
  }
}
