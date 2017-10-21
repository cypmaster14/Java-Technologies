package org.cypmaster.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Query2Servlet", urlPatterns = {"/query2"})
public class Query2Servlet extends HttpServlet {

    private final static String CATEGORY_FORM_NAME = "category";
    private final static String QUERY2_PAGE_LOCATION = "/WEB-INF/pages/query2.jsp";
    private final static String QUERY2_RESULT_PAGE_LOCATION = "/WEB-INF/pages/query2result.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(QUERY2_PAGE_LOCATION).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryId = req.getParameter(CATEGORY_FORM_NAME);
        req.setAttribute(CATEGORY_FORM_NAME, categoryId);
        req.getRequestDispatcher(QUERY2_RESULT_PAGE_LOCATION).forward(req, resp);
    }
}
