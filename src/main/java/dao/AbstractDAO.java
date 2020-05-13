package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public abstract class AbstractDAO<E, K> {
    public static String PERSISTENCE_UNIT_NAME = "entityManager";

    EntityManager em;

    public AbstractDAO(EntityManager em) {
        this.em = em;
    }

    public abstract List<E> getAll();

    public abstract void update(E entity);

    public abstract E getEntityById(K id);

    public abstract boolean deleteById(K id);

    public abstract void create(E entity);
}
