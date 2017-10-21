package org.cypmaster.servlets;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HomeServlet", urlPatterns = {"/home"}, loadOnStartup = 1)
public class HomeServlet extends HttpServlet {

    private final static String HOME_PAGE_LOCATION = "/home.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(HOME_PAGE_LOCATION).forward(req, resp);
    }

}
