package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constants.PageConstants;
import com.lftechnology.batch7crud.constants.ParameterConstants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by sagarmatha on 2/1/16.
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private final String userID = "admin";
    private final String password = "admin";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher(PageConstants.LOGIN_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");

        if(userID.equals(user) && password.equals(pwd)){
            HttpSession session = request.getSession();
            session.setAttribute(ParameterConstants.USER, userID);
            //setting session to expiry in 30 mins
            session.setMaxInactiveInterval(30 * 60);
            response.sendRedirect("/");
        }else{
            request.getServletContext().getRequestDispatcher(PageConstants.LOGIN_PAGE).forward(request, response);
        }

    }
}
