package org.cypmaster.servlets;

import org.cypmaster.entities.Category;
import org.cypmaster.services.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "inputServlet", urlPatterns = {"/input"}, loadOnStartup = 1)
public class InputServlet extends HttpServlet {

    private CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        categoryService = CategoryService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> availableCategories = getAvailableCategories();
        getServletContext().log("[CATEGORIES]" + availableCategories.toString());
        req.setAttribute("categories", availableCategories);
        req.getRequestDispatcher("input.jsp").forward(req, resp);
    }

    private synchronized List<Category> getAvailableCategories() {
        return categoryService.getCategories();
    }
}
