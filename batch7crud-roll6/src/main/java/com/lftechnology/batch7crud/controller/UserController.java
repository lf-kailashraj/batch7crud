package com.lftechnology.batch7crud.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.User;
import com.lftechnology.batch7crud.service.UserService;
import com.lftechnology.batch7crud.util.TypeCaster;

/**
 * @author madandhungana <madandhungana@lftechnology.com> Jan 18, 2016
 */

@WebServlet("/users/*")
public class UserController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());
    UserService userService = new UserService();
    private static final String REDIRECT_STRING = "/batch7crud-roll6/users";
    private static final int LIMIT = 10;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String urlString = request.getPathInfo();
        System.out.println("url:"+ urlString);

        if (urlString == null || "/".equals(urlString)) {
            try {
                this.list(request, response, 1);
            } catch (ServletException e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
            }

        } else {

            String[] pathArgs = urlString.split("/");

            if ("".equals(pathArgs[0]) && "add".equals(pathArgs[1])) {
                try {
                    create(request, response);
                } catch (ServletException e) {
                    LOGGER.log(Level.SEVERE, e.getMessage(), e);
                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, e.getMessage(), e);
                }
            } else if ("".equals(pathArgs[0]) && "edit".equals(pathArgs[2])) {
                try {
                    int userID = TypeCaster.toInt(pathArgs[1]);
                    edit(request, response, userID);
                } catch (ServletException e) {
                    LOGGER.log(Level.SEVERE, e.getMessage(), e);
                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, e.getMessage(), e);
                }
            }
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String urlString = request.getPathInfo();
        if (urlString == null) {
            try {
                this.list(request, response, 1);
            } catch (ServletException e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
            }
        } else {
            String[] pathArgs = urlString.split("/");
            if ("".equals(pathArgs[0]) && "add".equals(pathArgs[1])) {
                try {
                    createProcess(request, response);
                } catch (ServletException e) {
                    LOGGER.log(Level.SEVERE, e.getMessage(), e);
                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, e.getMessage(), e);
                }
            } else if ("".equals(pathArgs[0]) && "edit".equals(pathArgs[2])) {
                try {
                    int userID = TypeCaster.toInt(pathArgs[1]);
                    editProcess(request, response, userID);
                } catch (ServletException e) {
                    LOGGER.log(Level.SEVERE, e.getMessage(), e);
                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, e.getMessage(), e);
                }
            } else if ("".equals(pathArgs[0]) && "delete".equals(pathArgs[2])) {
                try {
                    int userID = TypeCaster.toInt(pathArgs[1]);
                    deleteProcess(request, response, userID);
                } catch (ServletException e) {
                    LOGGER.log(Level.SEVERE, e.getMessage(), e);
                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, e.getMessage(), e);
                }
            }

        }

    }

    private void list(HttpServletRequest request, HttpServletResponse response, int page) throws ServletException, IOException {
        try {
            request.setAttribute("users", userService.fetch(page, LIMIT));
            request.setAttribute("totalUsers", userService.totalUser());
            request.setAttribute("currentPage", page);
            request.getRequestDispatcher("/WEB-INF/views/listUser.jsp").forward(request, response);

        } catch (DataException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/addUser.jsp").forward(request, response);
    }

    private void createProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = new User();

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
            response.sendRedirect(REDIRECT_STRING);
        } catch (DataException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
        try {
            request.setAttribute("user", userService.fetchByID(id));
            request.getRequestDispatcher("/WEB-INF/views/editUser.jsp").forward(request, response);
        } catch (DataException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        }
    }

    private void editProcess(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {

        User user = new User();

        String firstName = request.getParameter("firstname");
        String surName = request.getParameter("surname");
        String username = request.getParameter("username");

        user.setFirstName(firstName);
        user.setSurName(surName);
        user.setUserName(username);
        user.setId(id);

        try {
            userService.update(user);
            response.sendRedirect(REDIRECT_STRING);
        } catch (DataException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        }
    }

    private void deleteProcess(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
        try {
            userService.deleteUser(id);
            response.sendRedirect(REDIRECT_STRING);

        } catch (DataException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

}
