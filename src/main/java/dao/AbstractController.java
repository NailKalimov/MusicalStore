package dao;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public abstract class AbstractController<E, K> {
    final EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getEntityManagerFactorySingleton().getEntityManagerFactory();

    public abstract List<E> getAll();

    public abstract void update(E entity);

    public abstract E getEntityById(K id);

    public abstract boolean deleteById(K id);

    public abstract void create(E entity);

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

}
