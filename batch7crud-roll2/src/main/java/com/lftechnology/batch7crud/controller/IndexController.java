package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.model.User;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/" })
public class IndexController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
        if(req.getSession().getAttribute("validUser")!=null){
            System.out.println("not null");
            req.getServletContext().getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
        }else{
            System.out.println("null");
            req.getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }
    }
}