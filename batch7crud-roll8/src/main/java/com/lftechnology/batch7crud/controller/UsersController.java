package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.User;
import com.lftechnology.batch7crud.services.UserServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Created by grishma on 1/18/16.
 */

@WebServlet({ "/users/*" })
public class UsersController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();

        if (path == null) {
            request.getServletContext().getRequestDispatcher("/WEB-INF/views/users/signup.jsp").forward(request, response);
        }
        else {
            String[] parts = path.split("/");
            if (parts[1].equals("create")) {
                create(request, response);
            }
            else if (parts[1].equals("update")) {
                request.getServletContext().getRequestDispatcher("/WEB-INF/views/users/index.jsp").forward(request, response);
            }
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();

        if (path == null) {
            request.getServletContext().getRequestDispatcher("/WEB-INF/views/users/index.jsp").forward(request,
                    response);
        }
        else {
            String[] parts = path.split("/");
            if (parts[1].equals("signup")) {
                request.getServletContext().getRequestDispatcher("/WEB-INF/views/users/signup.jsp").forward(request, response);
            }
            else if (parts[1].equals("signin")) {
                request.getServletContext().getRequestDispatcher("/WEB-INF/views/users/signin.jsp").forward(request, response);
            }
        }
    }

    private void fetch(HttpServletRequest request, HttpServletResponse response) throws
            ServletException,
            IOException {
        response.sendRedirect("/users");
    }

    private void create(HttpServletRequest request, HttpServletResponse response) {
        try {
            String name = request.getParameter("name");
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            UserServices userServices = new UserServices();

            User user = new User();
            user.setName(name);
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);

            userServices.create(user);
            fetch(request, response);
        } catch (DataException e) {
            e.printStackTrace();
            // check here for error and do required redirection and message display
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

