package org.cypmaster.dao;

import org.cypmaster.dto.UserInputDTO;
import org.cypmaster.entities.UserInput;

import java.util.List;
import java.util.concurrent.ExecutorService;

public interface UserDAO {

    void addInput(UserInput userInput, int categoryId) throws Exception;

    List<UserInputDTO> getAllInputs();

    UserInputDTO getInputByKey(String key);
}
