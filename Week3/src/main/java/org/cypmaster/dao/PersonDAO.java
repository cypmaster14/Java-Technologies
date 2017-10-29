package org.cypmaster.dao;

import org.cypmaster.entities.Person;

import java.util.Optional;

public interface PersonDAO {


    Optional<Person> findByUsername(String username);

}
