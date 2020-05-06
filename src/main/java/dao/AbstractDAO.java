package dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public abstract class AbstractDAO<E, K> {
    public static String PERSISTENCE_UNIT_NAME = "entityManager";
    final EntityManagerFactory entityManagerFactory;

    public AbstractDAO() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public abstract List<E> getAll();

    public abstract void update(E entity);

    public abstract E getEntityById(K id);

    public abstract boolean deleteById(K id);

    public abstract void create(E entity);

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public void closeEntityManagerFactory() {
        entityManagerFactory.close();
    }
}
