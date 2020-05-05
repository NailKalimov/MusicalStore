package dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactorySingleton {
    public static final String FILE_NAME = "entityManager";
    private static EntityManagerFactorySingleton entityManagerFactorySingleton;
    private final EntityManagerFactory entityManagerFactory;


    private EntityManagerFactorySingleton() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory(FILE_NAME);
    }

    public void closeEntityManagerFactory() {
        entityManagerFactory.close();
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public static EntityManagerFactorySingleton getEntityManagerFactorySingleton() {
        if (entityManagerFactorySingleton == null) {
            entityManagerFactorySingleton = new EntityManagerFactorySingleton();
        }
        return entityManagerFactorySingleton;
    }
}
