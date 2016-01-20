package com.lftechnology.batch7crud.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author madandhungana <madandhungana@lftechnology.com> Jan 18, 2016
 */
@WebServlet("/")
public class IndexController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            request.getRequestDispatcher("WEB-INF/views/index.jsp").forward(request, response);
        }catch(Exception e){
            LOGGER.log(Level.SEVERE,e.getMessage(),e);
        }
    }

}
