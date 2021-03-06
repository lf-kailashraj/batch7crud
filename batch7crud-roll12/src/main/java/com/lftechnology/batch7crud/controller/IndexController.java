package com.lftechnology.batch7crud.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lftechnology.batch7crud.util.TypeCaster;

@WebServlet({ "/" })
public class IndexController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer i = TypeCaster.toInt("123");
        System.out.println("value of i: " + i);
        req.getServletContext().getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    };
}