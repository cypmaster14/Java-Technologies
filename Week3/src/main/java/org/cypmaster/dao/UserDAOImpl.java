package org.cypmaster.dao;

import org.cypmaster.dto.UserInputDTO;
import org.cypmaster.entities.Category;
import org.cypmaster.entities.UserInput;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private EntityManager entityManager;
    private final static String getAllInputs = "SELECT u from UserInput u";

    public UserDAOImpl() {
        this.entityManager = Persistence
                .createEntityManagerFactory("MyPersistenceUnit")
                .createEntityManager();
    }

    @Override
    public void addInput(UserInput userInput, int categoryId) throws Exception {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Category category = entityManager.getReference(Category.class, categoryId);
            userInput.setCategory(category);
            entityManager.persist(userInput);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<UserInputDTO> getAllInputs() {

        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Query query = entityManager.createQuery(getAllInputs);
            List<UserInput> inputs = query.getResultList();
            transaction.commit();
            List<UserInputDTO> inputDTO = new ArrayList<>();
            for (UserInput userInput : inputs) {
                inputDTO.add(new UserInputDTO(userInput.getName(), userInput.getValue(), userInput.getCategory().getName()));
            }
            return inputDTO;

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return Collections.emptyList();
        }


    }

    @Override
    public UserInputDTO getInputByKey(String key) {

        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            UserInput userInput = entityManager.find(UserInput.class, key);
            UserInputDTO userInputDTO = new UserInputDTO(userInput.getName(), userInput.getValue(), userInput.getCategory().getName());
            transaction.commit();
            return userInputDTO;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return new UserInputDTO();
        }
    }
}
