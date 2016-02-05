package com.lftechnology.batch7crud.parser;

import com.lftechnology.batch7crud.constants.UrlConstants;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by sagarmatha on 2/5/16.
 */
public class UrlParser {
  String[] parameters;

  public int parse(HttpServletRequest req) {
    String urlPath = req.getRequestURI().substring(req.getContextPath().length());
    parameters = urlPath.split(File.separator);
    int actionIndicator = 0;
    if (parameters.length == 2)
      actionIndicator = lengthTwoPathParser();
    else if (parameters.length == 3)
      actionIndicator = lengthThreePathParser();
    else if (parameters.length == 4)
      actionIndicator = lengthFourPathParser();
    return actionIndicator;
  }

  private int lengthTwoPathParser() {
    if (UrlConstants.STUDENTS.equals(parameters[1]))
      return 1;
    return 0;
  }

  private int lengthThreePathParser() {
    if (UrlConstants.CREATE.equals(parameters[2]))
      return 2;
    return 0;
  }

  private int lengthFourPathParser() {
    if (UrlConstants.UPDATE.equals(parameters[3])) {
      return 3;
    } else if (UrlConstants.VIEW.equals(parameters[3])) {
      return 4;
    } else if (UrlConstants.DELETE.equals(parameters[3])) {
      return 5;
    }
    return 0;
  }
}
