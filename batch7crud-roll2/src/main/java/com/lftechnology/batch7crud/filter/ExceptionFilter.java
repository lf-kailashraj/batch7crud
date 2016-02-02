package com.lftechnology.batch7crud.filter;

import com.lftechnology.batch7crud.constants.AppConstants;
import com.lftechnology.batch7crud.constants.AttributeConstants;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Romit Amgai <romitamgai@lftechnology.com> on 1/28/16.
 */
public class ExceptionFilter implements Filter {
  private static final Logger LOGGER = Logger.getLogger(ExceptionFilter.class.getName());

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    //initializations can be done here
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
    try {
      filterChain.doFilter(request, response);
    } catch (ServletException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      request.setAttribute(AttributeConstants.ERROR_MESSAGE, ex.getMessage());
      if (AppConstants.PAGE_NOT_FOUND_MESSAGE.equals(ex.getMessage())) {
        ((HttpServletResponse) response).sendError(HttpServletResponse.SC_NOT_FOUND);
      } else {
        ((HttpServletResponse) response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      }
    }
  }

  @Override
  public void destroy() {
    //Filter can be destroyed here
  }
}
