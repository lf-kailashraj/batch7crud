package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constants.PageConstants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by sagarmatha on 2/2/16.
 */
@WebServlet("/LogoutController")
public class LogoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //invalidate the session if exists
        HttpSession session = request.getSession(false);
        String loginPath = request.getContextPath() + "/LoginController";
        if(session != null){
            session.invalidate();
        }
        response.sendRedirect(loginPath);
    }
}
