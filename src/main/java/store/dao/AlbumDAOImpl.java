package store.dao;

import store.entity.Album;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import java.util.List;

public class AlbumDAOImpl implements AlbumDAO {

    private EntityManager em;

    public AlbumDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Album> getAll() {
        return em.createQuery("FROM Album", Album.class).getResultList();
    }

    @Override
    public void update(Album entity) {
        em.getTransaction().begin();
        em.merge(entity);
        try {
            em.getTransaction().commit();
        } catch (RollbackException rollbackException){
            em.getTransaction().rollback();
            rollbackException.printStackTrace();
        }
    }

    @Override
    public Album getEntityById(Long id) {
        return em.createQuery("from Album where id = :id", Album.class).
                setParameter("id", id).
                getSingleResult();
    }

    @Override
    public boolean deleteById(Long id) {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Track where id=:id").
                setParameter("id", id).
                executeUpdate();
        int res = em.createQuery("DELETE Album WHERE id=:id").
                setParameter("id", id).executeUpdate();
        try {
            em.getTransaction().commit();
        } catch (RollbackException rollbackException){
            em.getTransaction().rollback();
            rollbackException.printStackTrace();
        }
        return res > 0;
    }

    @Override
    public void create(Album entity) {
        em.getTransaction().begin();
        em.persist(entity);
        try {
            em.getTransaction().commit();
        } catch (RollbackException r){
            em.getTransaction().rollback();
            r.printStackTrace();
        }
    }

    public void closeEntityManager(){
        em.close();
    }

}
