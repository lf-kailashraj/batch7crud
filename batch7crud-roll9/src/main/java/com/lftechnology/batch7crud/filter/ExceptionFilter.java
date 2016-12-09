package com.lftechnology.batch7crud.filter;

import com.lftechnology.batch7crud.constants.AttributeConstant;
import com.lftechnology.batch7crud.constants.MessageConstant;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExceptionFilter implements Filter {

  private static final Logger LOGGER = Logger.getLogger(ExceptionFilter.class.getName());

  @Override
  public void destroy() { // NOSONAR

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
          throws IOException, ServletException {
    try {
      chain.doFilter(request, response);
    } catch (ServletException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      request.setAttribute(AttributeConstant.ERROR_MESSAGE, e.getMessage());
      if (MessageConstant.PAGE_NOT_FOUND.equals(e.getMessage())) {
        ((HttpServletResponse) response).sendError(HttpServletResponse.SC_NOT_FOUND);
      } else {
        ((HttpServletResponse) response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      }
    }
  }

  @Override
  public void init(FilterConfig fConfig) throws ServletException { // NOSONAR
  }
}