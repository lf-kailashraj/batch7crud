package com.lftechnology.batch7crud.filter;

import com.lftechnology.batch7crud.constant.PageConstant;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**This class handles all the exceptions and sends error code corresponding to the exception message
 *
 * @Author Binod Shrestha <binodshrestha@lftechnology.com>
 * Created on 1/29/16
 */
public class ExceptionFilter implements Filter {

  public static final Logger LOGGER = Logger.getLogger(ExceptionFilter.class.getName());
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {   //NOSONAR

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    try{
      chain.doFilter(request, response);
    }catch (ServletException e){
      LOGGER.log(Level.SEVERE, e.getMessage(), e);

      if(PageConstant.INTERNAL_SERVER_ERROR.equalsIgnoreCase(e.getMessage())){
        ((HttpServletResponse) response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      }else if(PageConstant.PAGE_NOT_FOUND.equalsIgnoreCase(e.getMessage())){
        ((HttpServletResponse) response).sendError(HttpServletResponse.SC_NOT_FOUND);
      }
    }
  }

  @Override
  public void destroy() {   //NOSONAR

  }
}
