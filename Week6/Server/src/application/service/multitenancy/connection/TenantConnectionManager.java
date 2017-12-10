package application.service.multitenancy.connection;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by SilviuG on 19.11.2017.
 */
public interface TenantConnectionManager {
    Connection getConnection() throws NamingException, SQLException;
}
