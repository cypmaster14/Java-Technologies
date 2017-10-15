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

    private static final String CATEGORIES_ATTRIBUTE_NAME = "categories";
    public static final String INPUT_PAGE_LOCATION = "/input.jsp";

    private CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        categoryService = CategoryService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> availableCategories = getAvailableCategories();
        getServletContext().log("[CATEGORIES]" + availableCategories.toString());
        req.setAttribute(CATEGORIES_ATTRIBUTE_NAME, availableCategories);

        req.getRequestDispatcher(INPUT_PAGE_LOCATION).forward(req, resp);
    }

    private synchronized List<Category> getAvailableCategories() {
        return categoryService.getCategories();
    }
}
