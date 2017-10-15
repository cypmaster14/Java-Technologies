package org.cypmaster.services;

import org.cypmaster.dao.CategoryDAO;
import org.cypmaster.dao.CategoryDAOImpl;
import org.cypmaster.entities.Category;

import java.util.List;

public class CategoryService {
    private static CategoryService ourInstance;

    private CategoryDAO categoryDAO;

    public synchronized static CategoryService getInstance() {
        if (ourInstance == null) {
            ourInstance = new CategoryService();
        }
        return ourInstance;
    }

    private CategoryService() {
        categoryDAO = new CategoryDAOImpl();
    }

    public List<Category> getCategories() {
        return categoryDAO.getCategories();
    }

    public void addCategory(String categoryName) {
        Category category = new Category();
        category.setName(categoryName);
        categoryDAO.addCategory(category);
    }
}
