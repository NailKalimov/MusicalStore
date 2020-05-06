package dao;

import entity.Track;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import java.util.List;

public class TrackDAO extends AbstractDAO<Track, Long> {
    EntityManager em = entityManagerFactory.createEntityManager();
    @Override
    public List<Track> getAll() {
        return em.createQuery("from Track", Track.class).getResultList();
    }

    @Override
    public void update(Track entity) {
        em.getTransaction().begin();
        em.merge(entity);
        try {
            em.getTransaction().commit();
        } catch (RollbackException r){
            em.getTransaction().rollback();
            r.printStackTrace();
        }
    }

    @Override
    public Track getEntityById(Long id) {
        return em.createQuery("from Track where trackId = :id", Track.class).
                setParameter("id", id).
                getSingleResult();
    }

    @Override
    public boolean deleteById(Long id) {
        em.getTransaction().begin();
        int res = em.createQuery("DELETE Track WHERE trackId=:id").
                setParameter("id", id).executeUpdate();
        try {
            em.getTransaction().commit();
        } catch (RollbackException r){
            em.getTransaction().rollback();
            r.printStackTrace();
        }
        return res > 0;
    }

    @Override
    public void create(Track entity) {
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
