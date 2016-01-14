package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.dao.FormDao;
import com.lftechnology.batch7crud.model.FormInformation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by romit on 1/14/16.
 */
@WebServlet({ "/Display" })
public class DisplayController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String contact = request.getParameter("contact");

        FormInformation formInfo = new FormInformation();
        formInfo.setUserName(name);
        formInfo.setAddress(address);
        formInfo.setEmail(email);
        formInfo.setContact(contact);

        FormDao formDao = new FormDao();
        formDao.insertFormData(formInfo);

       // List<FormInformation> formDataList = formDao.getFormDataList();
        //request.setAttribute("formdatalist",formDataList);
        request.setAttribute("name",name);
        request.setAttribute("address",address);
        request.setAttribute("email", email);
        request.setAttribute("contact", contact);
        request.getRequestDispatcher("/WEB-INF/views/display.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
