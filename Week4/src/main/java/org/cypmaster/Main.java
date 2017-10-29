package org.cypmaster;


import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    @Resource(name = "jdbc/mysql")
    private DataSource dataSource;


    public boolean testConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        boolean test = false;
        try {
            test = connection.isValid(5);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return test;
    }

    public static void main(String[] args) {

        Main main = new Main();
        System.out.println(main.testConnection());

    }

}
