package org.cypmaster.utils;

import java.util.concurrent.ExecutorService;

public class PersonalException extends RuntimeException {

    public PersonalException(String message) {
        super(message);
    }
}
