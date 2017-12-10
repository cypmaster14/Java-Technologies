package application.service;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SilviuG on 19.11.2017.
 */
public class ServiceLocator {

    private static ServiceLocator INSTANCE;
    private InitialContext ic;
    private Map<String, Object> cache;

    public static ServiceLocator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServiceLocator();
        }
        return INSTANCE;
    }

    private ServiceLocator() {
        try {
            ic = new InitialContext();
            cache = Collections.synchronizedMap(new HashMap<>());
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }

    private Object lookup(String jndiName) throws NamingException {
        Object cachedObj = cache.get(jndiName);
        if (cachedObj == null) {
            cachedObj = ic.lookup("java:comp/env/" + jndiName);
            cache.put(jndiName, cachedObj);
        }
        return cachedObj;
    }

    public DataSource getDataSource(String dataSourceName)
            throws NamingException {
        return (DataSource) lookup(dataSourceName);
    }
}
