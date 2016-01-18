package com.lftechnology.batch7crud.controller;

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
        System.out.println("test from post");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        System.out.println(path);

        if (path == null) {
            request.getServletContext().getRequestDispatcher("/WEB-INF/views/users/signup.jsp").forward(request, response);
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
}

