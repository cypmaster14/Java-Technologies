package org.cypmaster.dao;

import org.cypmaster.entities.Person;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PersonDAOImpl implements PersonDAO {

    private List<Person> personList;

    public PersonDAOImpl() {
        personList = Arrays.asList(
                new Person("admin", "admin"),
                new Person("admin2", "admin")

        );
    }

    @Override
    public Optional<Person> findByUsername(String username) {
        return personList
                .stream()
                .filter(person -> person.getUsername().equals(username))
                .findAny();
    }
}
