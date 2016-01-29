package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constant.PageConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This is custom HttpServlet that is used to handle the common operations of all servlets.
 *
 * @Author Binod Shrestha <binodshrestha@lftechnology.com>
 * Created on 1/19/16
 */
public abstract class CustomHttpServlet extends HttpServlet {

  public String paramsTest(HttpServletRequest req){   //NOSONAR
    String servletPath = req.getPathInfo();

    if(servletPath == null || "/".equalsIgnoreCase(servletPath)){
      return PageConstant.LIST;
    }

    String[] urlTokens = servletPath.split("/");

    if(urlTokens.length == 2 && PageConstant.CREATE.equalsIgnoreCase(urlTokens[1])){
      return PageConstant.CREATE;
    }

    if(urlTokens.length == 2 && PageConstant.LIST.equalsIgnoreCase(urlTokens[1])){
      return PageConstant.LIST;
    }

    if(urlTokens.length == 3 && PageConstant.EDIT.equalsIgnoreCase(urlTokens[2])){

      try{
        Integer.parseInt(urlTokens[1]);
        return PageConstant.EDIT;
      }catch (NumberFormatException e){
        return null;
      }
    }

    if(urlTokens.length == 3 && PageConstant.DELETE.equalsIgnoreCase(urlTokens[2])){
      try{
        Integer.parseInt(urlTokens[1]);
        return PageConstant.DELETE;
      }catch (NumberFormatException e){
        return null;
      }
    }

    if(urlTokens.length == 2 && PageConstant.LOGIN.equalsIgnoreCase(urlTokens[1])){
      return PageConstant.LOGIN;
    }

    if(urlTokens.length == 2 && PageConstant.LOGOUT.equalsIgnoreCase(urlTokens[1])){
      return PageConstant.LOGOUT;
    }

    return null;

  }
}
