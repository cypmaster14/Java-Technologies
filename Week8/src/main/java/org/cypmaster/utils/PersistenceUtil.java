package org.cypmaster.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Ciprian at 12/4/2017
 */
public class PersistenceUtil {

    private static EntityManagerFactory emf;

    public final static String PERSISTENCE_UNIT_NAME = "Week7";
    public final static String PERSISTENCE_UNIT_TEST_NAME = "Week7Test";

    private PersistenceUtil() {
        this.emf = Persistence.createEntityManagerFactory("Week7");
    }

    public synchronized static EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return emf.createEntityManager();
    }

    public static EntityManagerFactory getEntityManagerFactory(String persistenceUnitName) {
        return Persistence.createEntityManagerFactory(persistenceUnitName);
    }

}
