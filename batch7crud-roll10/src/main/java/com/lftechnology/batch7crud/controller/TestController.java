package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.utils.requestmapper.RequestMapping;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author binodnme
 * Created on 2/3/16
 */
@WebServlet("/test/*")
public class TestController extends HttpServlet{

  @RequestMapping(value = "testing", method = "GET")
  private void testMethod(HttpServletRequest req, HttpServletResponse resp){
    System.out.println("inside test method");
  }
}
