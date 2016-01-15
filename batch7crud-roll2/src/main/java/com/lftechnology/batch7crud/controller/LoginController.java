package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.model.User;
import com.lftechnology.batch7crud.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by romit on 1/14/16.
 */
@WebServlet(name = "LoginController", urlPatterns = {"/Login"})
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        User user, validUser;
        user = new User();
        user.setUserName(userName);
        user.setPassword(password);

        try{
            UserService userService = new UserService();
            validUser = userService.getValidUser(user);

            HttpSession session = request.getSession();

           if(validUser!=null){
               session.setAttribute("validUser", validUser);
               response.sendRedirect("/");
           }
           else {
               response.sendRedirect("/Display");
           }
        }catch (Exception e){
            e.printStackTrace();
            System.out.print("Error in login controller");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
