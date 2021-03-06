package org.cypmaster.utils;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtil {

    private DataSource dataSource = null;

    private static ConnectionUtil INSTANCE;

    public Connection connection;

    private ConnectionUtil() throws SQLException, NamingException {
        Context initialContext = new InitialContext();
        Context envContext = (Context) initialContext.lookup("java:comp/env");
        this.dataSource = (DataSource) envContext.lookup("jdbc/mysql");
        this.connection = dataSource.getConnection();
    }

    public synchronized static ConnectionUtil getInstance() {
        if (INSTANCE == null) {
            try {
                INSTANCE = new ConnectionUtil();
            } catch (SQLException | NamingException e) {
                e.printStackTrace();
            }
        }
        return INSTANCE;
    }
}
