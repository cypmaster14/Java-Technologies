package org.cypmaster.services;

import org.cypmaster.dao.PersonDAO;
import org.cypmaster.dao.PersonDAOImpl;
import org.cypmaster.entities.Person;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PersonService {

    private static PersonService instance;

    private PersonDAO personDAO;
    private static Map<String, String> REMEMBERED_USERS = new HashMap<>();
    private static SecureRandom RANDOM = new SecureRandom();

    private PersonService() {
        this.personDAO = new PersonDAOImpl();
    }

    public synchronized static PersonService getInstance() {
        if (instance == null) {
            instance = new PersonService();
        }
        return instance;
    }

    public Optional<Person> doLogin(String username, String password) {
        Optional<Person> user = personDAO.findByUsername(username);
        if (!user.isPresent() || !user.get().getPasword().equals(password)) {
            return Optional.empty();
        }
        return user;
    }


    public String rememberUser(String username) {
        String randomID = new BigInteger(130, RANDOM).toString(32);
        REMEMBERED_USERS.put(randomID, username);
        return randomID;
    }

    public String getRememberedUser(String id) {
        return REMEMBERED_USERS.get(id);
    }

    public void removeRememeredUser(String id) {
        REMEMBERED_USERS.remove(id);
    }

}
