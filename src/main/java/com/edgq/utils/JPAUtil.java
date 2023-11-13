package com.edgq.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author edwin
 */
public class JPAUtil {

    private static EntityManagerFactory factory;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory(Constants.PERSISTENCE_UNIT);
        }
        return factory;
    }

    public static void shutdown() {
        if (factory != null) {
            factory.close();
        }
    }

}
