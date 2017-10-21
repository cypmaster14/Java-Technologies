package org.cypmaster.tests;

import org.cypmaster.entities.Category;
import org.cypmaster.services.CategoryService;

import java.util.List;

public class CategoryInsert {

    public static void main(String[] args) {

        CategoryService categoryService = CategoryService.getInstance();
        List<Category> categories = categoryService.getCategories();
        System.out.println(categories);

    }
}
