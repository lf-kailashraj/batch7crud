package com.lftechnology.batch7crud.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.lftechnology.batch7crud.constant.AttribConstants.ATTRIB_MESSAGE;
import static com.lftechnology.batch7crud.constant.MessageConstants.MESSAGE_PAGE_NOT_FOUND;

/**
 * Created by pratishshr on 1/29/16.
 */
public class ExceptionFilter implements Filter{
  private static final Logger LOGGER = Logger.getLogger(ExceptionFilter.class.getName());

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    return;
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
          throws IOException, ServletException {
     try {
       filterChain.doFilter(servletRequest, servletResponse);
     }catch (ServletException e) {
       LOGGER.log(Level.SEVERE, e.getMessage(), e);
       servletRequest.setAttribute(ATTRIB_MESSAGE, e.getMessage());

       if(MESSAGE_PAGE_NOT_FOUND.equals(e.getMessage())) {
         ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_NOT_FOUND);
       } else{
         ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
       }

    }
  }

  @Override
  public void destroy() {
    return;
  }
}
