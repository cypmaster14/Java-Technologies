package org.cypmaster.entities;

public class Person {

    private String username;
    private String pasword;

    public Person() {
    }

    public Person(String username, String pasword) {
        this.username = username;
        this.pasword = pasword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }
}
