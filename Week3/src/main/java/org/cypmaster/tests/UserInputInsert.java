package org.cypmaster.tests;

import org.cypmaster.services.UserService;

public class UserInputInsert {

    public static void main(String[] args) throws Exception {
        UserService userService = UserService.getInstance();
        userService.addUserInput("456", "def", 3);

    }
}
