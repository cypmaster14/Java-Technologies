package org.cypmaster.tests;

import org.cypmaster.dto.UserInputDTO;
import org.cypmaster.services.UserService;

import java.util.List;

public class UserInputRetriever {

    public static void main(String[] args) {

        UserService userService = UserService.getInstance();
        List<UserInputDTO> inputs = userService.getInputs();
        System.out.println(inputs);

    }
}
