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

@WebServlet("/users/*")
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserService();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String urlString = request.getPathInfo();

        if (urlString == null) {
            list(request, response, 1);

        } else {

            String[] pathArgs = urlString.split("/");

            if (pathArgs[0].equals("") && pathArgs[1].equals("add")) {
                create(request, response);
            }

        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String urlString = request.getPathInfo();
        if (urlString == null) {
            list(request, response, 1);
        } else {
            String[] pathArgs = urlString.split("/");
            if (pathArgs[0].equals("") && pathArgs[1].equals("add")) {
                createProcess(request, response);
            }

        }

    }

    private void list(HttpServletRequest request, HttpServletResponse response, int page) throws ServletException, IOException {
        try {
            request.setAttribute("users", userService.fetch(1));
            request.getRequestDispatcher("/WEB-INF/views/listUser.jsp").forward(request, response);

        } catch (DataException e) {
            e.printStackTrace();
        }
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/addUser.jsp").forward(request, response);
    }

    private void createProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
            response.sendRedirect("/batch7crud-roll6/users");
        } catch (DataException e) {
            System.err.println(e);
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
    }

    private void editProcess(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
    }

    private void deleteProcess(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
    }

}
