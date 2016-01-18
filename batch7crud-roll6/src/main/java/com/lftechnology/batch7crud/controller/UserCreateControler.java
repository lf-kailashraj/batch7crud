package com.lftechnology.batch7crud.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.User;
import com.lftechnology.batch7crud.service.UserService;

/**
 * @author madandhungana <madandhungana@lftechnology.com> Jan 18, 2016
 */
@WebServlet("/addUser")
public class UserCreateControler extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCreateControler() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/views/addUser.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = new User();
        UserService userService = new UserService();

        String firstName = request.getParameter("firstname");
        String surName = request.getParameter("surname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        user.setFirstName(firstName);
        user.setSurName(surName);
        user.setUserName(username);
        user.setPassword(password);

        try {
            userService.addUser(user);
            response.sendRedirect("/batch7crud-roll6/userList");
        } catch (DataException e) {
            System.err.println(e);
        }

    }

}
