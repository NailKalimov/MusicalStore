package store.dao;

import java.util.List;

public interface InterfaceDAO<E, K> {

    public abstract List<E> getAll();

    public abstract E getEntityById(K id);

    public abstract void update(E entity);

    public abstract boolean deleteById(K id);

    public abstract void create(E entity);
}
