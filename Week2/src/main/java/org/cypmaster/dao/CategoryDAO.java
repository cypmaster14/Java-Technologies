package org.cypmaster.dao;

import org.cypmaster.entities.Category;

import java.util.List;

public interface CategoryDAO {

    List<Category> getCategories();

    void addCategory(Category category);
}
