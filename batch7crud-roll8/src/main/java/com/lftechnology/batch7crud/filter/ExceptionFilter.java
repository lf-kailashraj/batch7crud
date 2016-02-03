package com.lftechnology.batch7crud.filter;

import com.lftechnology.batch7crud.constants.AppConstants;
import com.lftechnology.batch7crud.constants.AttributeConstants;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Grishma Shrestha grishmashrestha@lftechnology.com on 1/29/16.
 */
public class ExceptionFilter implements Filter {
  private static final Logger LOGGER = Logger.getLogger(ExceptionFilter.class.getName());

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
    try {
      chain.doFilter(request, response);
    } catch (ServletException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      request.setAttribute(AttributeConstants.MESSAGE, e.getMessage());

      if (AppConstants.PAGE_NOT_FOUND_MESSAGE.equals(e.getMessage())) {
        ((HttpServletResponse) response).sendError(HttpServletResponse.SC_NOT_FOUND);
      } else {
        ((HttpServletResponse) response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      }
    }
  }
}
