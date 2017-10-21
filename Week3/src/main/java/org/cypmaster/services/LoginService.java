package org.cypmaster.services;

public class LoginService {

    private static LoginService instance;

    private final static String USERNAME_MOCK = "admin";
    private final static String PASSWORD_MOCK = "admin";

    private LoginService() {

    }

    public static synchronized LoginService getInstance() {
        if (instance == null) {
            instance = new LoginService();
        }
        return instance;
    }

    public boolean checkCredentials(String username, String password) {
        return username.equals(USERNAME_MOCK) && password.equals(PASSWORD_MOCK);
    }

}
