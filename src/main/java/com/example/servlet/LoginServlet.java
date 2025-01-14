package com.example.servlet;

import com.example.Users;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        if (req.getSession()
                .getAttribute("user") != null) {
            dispatcher = req.getRequestDispatcher("/user/hello.jsp");
        } else {
            dispatcher = req.getRequestDispatcher("/login.jsp");
        }
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (Users.getInstance()
                .getUsers()
                .contains(login)
                && password.length() > 0) {
            req.getSession()
                    .setAttribute("user", login);
            resp.sendRedirect("/login");
        } else {
            req.getRequestDispatcher("/login.jsp")
                    .forward(req, resp);
        }
    }
}
