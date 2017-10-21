package org.cypmaster.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Query1Servlet", urlPatterns = {"/query1"})
public class Query1Servlet extends HttpServlet {


    private final static String KEY_FORM_NAME = "key";
    private final static String QUERY1_RESULT_PAGE_LOCATION = "/WEB-INF/pages/query1result.jsp";
    private final static String QUERY1_PAGE_LOCATION = "/WEB-INF/pages/query1.jsp";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(QUERY1_PAGE_LOCATION).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String key = req.getParameter(KEY_FORM_NAME);
        req.setAttribute(KEY_FORM_NAME, key);
        req.getRequestDispatcher(QUERY1_RESULT_PAGE_LOCATION).forward(req, resp);
    }
}
