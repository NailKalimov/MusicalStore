package dao;

import entity.Album;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import java.util.List;

public class AlbumDAO extends AbstractDAO<Album, Long> {
    EntityManager em = entityManagerFactory.createEntityManager();
    @Override
    public List<Album> getAll() {
        List<Album> result = em.createQuery("from Album", Album.class).getResultList();
        return result;
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
        return em.createQuery("from Album where albumId = :id", Album.class).
                setParameter("id", id).
                getSingleResult();
    }

    @Override
    public boolean deleteById(Long id) {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Track where album.albumId=:id").
                setParameter("id", id).
                executeUpdate();
        int res = em.createQuery("DELETE Album WHERE albumId=:id").
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

}
