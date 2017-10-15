package org.cypmaster.services;

import org.cypmaster.dao.UserDAO;
import org.cypmaster.dao.UserDAOImpl;
import org.cypmaster.dto.UserInputDTO;
import org.cypmaster.entities.UserInput;

import java.util.List;

public class UserService {

    private static UserService ourInstance;

    private UserDAO userDAO;

    private UserService() {
        this.userDAO = new UserDAOImpl();
    }

    public synchronized static UserService getInstance() {
        if (ourInstance == null) {
            ourInstance = new UserService();
        }
        return ourInstance;
    }

    public List<UserInputDTO> getInputs() {
        return userDAO.getAllInputs();
    }

    public void addUserInput(String name, String value, int categoryId) throws Exception {
        UserInput userInput = new UserInput();
        userInput.setName(name);
        userInput.setValue(value);

        userDAO.addInput(userInput, categoryId);
    }
}
