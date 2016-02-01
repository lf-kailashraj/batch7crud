package com.lftechnology.batch7crud.util;

import com.lftechnology.batch7crud.constants.ParameterConstant;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by sanjay on 2/1/16.
 */
public class UrlUtil {
  public static int pageNumber(HttpServletRequest request) {
    if (request.getParameter(ParameterConstant.PAGE) != null) {
      return Integer.parseInt(request.getParameter(ParameterConstant.PAGE));
    } else {
      return 1;
    }
  }

  public static String[] parameterValues(HttpServletRequest request) {
    String urlPath = request.getRequestURI().substring(request.getContextPath().length());
    return urlPath.split(File.separator);
  }

  public static int parameterValueAsInt(HttpServletRequest request, int index) {
    String[] paths = parameterValues(request);
    return Integer.parseInt(paths[index]);
  }
}
