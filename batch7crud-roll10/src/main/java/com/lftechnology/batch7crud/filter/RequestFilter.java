package com.lftechnology.batch7crud.filter;

import com.lftechnology.batch7crud.constant.PageConstant;
import com.lftechnology.batch7crud.utils.requestmapper.RequestMapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author binodnme
 * Created on 1/29/16
 */
public class RequestFilter implements Filter {
  private static final Logger LOGGER = Logger.getLogger(RequestFilter.class.getName());

  @Override
  public void init(FilterConfig filterConfig) throws ServletException { // NOSONAR

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;
    String uri = req.getRequestURI();

    if(uri.startsWith("/static")){
      chain.doFilter(req, resp);
    }else{
      try{
        RequestMapper requestMapper = new RequestMapper();
        requestMapper.map(req, resp);
      }catch (ServletException e){
        LOGGER.log(Level.SEVERE, e.getMessage(), e);

        if(PageConstant.INTERNAL_SERVER_ERROR.equalsIgnoreCase(e.getMessage())){
          ((HttpServletResponse) response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }else if(PageConstant.PAGE_NOT_FOUND.equalsIgnoreCase(e.getMessage())){
          ((HttpServletResponse) response).sendError(HttpServletResponse.SC_NOT_FOUND);
        }
      }
    }
  }

  @Override
  public void destroy() { // NOSONAR

  }
}
