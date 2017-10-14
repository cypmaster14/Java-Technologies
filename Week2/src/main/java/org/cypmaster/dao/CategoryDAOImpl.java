package org.cypmaster.dao;

import org.cypmaster.entities.Category;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class CategoryDAOImpl implements CategoryDAO {

    private EntityManager entityManager;
    private final static String PERSISTENCE_UNIT_NAME = "MyPersistenceUnit";

    private final String getCategoriesQuery = "SELECT c from Category c";

    public CategoryDAOImpl() {
        this.entityManager = Persistence
                .createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
                .createEntityManager();
    }

    @Override
    public List<Category> getCategories() {

        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            Query query = entityManager.createQuery(getCategoriesQuery);
            List<Category> categories = query.getResultList();
            entityTransaction.commit();
            return categories;
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void addCategory(Category category) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.persist(category);
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
