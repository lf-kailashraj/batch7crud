package com.lftechnology.batch7crud.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.service.UserService;

/**
 * @author madandhungana <madandhungana@lftechnology.com>
 * Jan 18, 2016
 */
@WebServlet("/userList")
public class UserListController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserListController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserService userService = new UserService();

        try {
            request.setAttribute("users", userService.fetch(1));
            request.getRequestDispatcher("WEB-INF/views/listUser.jsp").forward(request, response);
        } catch (DataException e) {
            System.err.println(e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);            
        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
