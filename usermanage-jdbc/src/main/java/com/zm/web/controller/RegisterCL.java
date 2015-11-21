package com.zm.web.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Register Controller
 * Created by Liu Yuhui on 2015/11/20.
 */
public class RegisterCL extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // parameters
        String usernmae = req.getParameter("username");
        String nickname = req.getParameter("nickname");

        if(null == usernmae || usernmae.isEmpty() || null == nickname || nickname.isEmpty()) {
            req.setAttribute("errmsg", "usernmae or nickname invalid");;
            req.getRequestDispatcher("/Register").forward(req, resp);
        }

        String password = req.getParameter("password");
        String password1 = req.getParameter("password1");

        if(null == password || null == password1 || !password.equals(password1)) {
            req.setAttribute("errmsg", "incorrect password");
            req.getRequestDispatcher("/Register").forward(req, resp);
        }

        String gender = req.getParameter("gender");
        int intGender;
        try {
            intGender = Integer.valueOf(gender);
        } catch (NumberFormatException e) {
            req.setAttribute("errmsg", "incorrect gender");
            req.getRequestDispatcher("/Register").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
