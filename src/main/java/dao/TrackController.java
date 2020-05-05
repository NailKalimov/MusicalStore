package dao;

import entity.Track;

import javax.persistence.EntityManager;
import java.util.List;

public class TrackController extends AbstractController<Track, Long> {
    @Override
    public List<Track> getAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Track> res = em.createQuery("from Track", Track.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return res;
    }

    @Override
    public void update(Track entity) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Track getEntityById(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Track result = em.createQuery("from Track where id = :name", Track.class).
                setParameter("name", id).
                getSingleResult();
        em.getTransaction().commit();
        em.close();
        return result;
    }

    @Override
    public boolean deleteById(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        int res = em.createQuery("DELETE Track WHERE id=:id").
                setParameter("id", id).executeUpdate();
        em.getTransaction().commit();
        em.close();
        return res > 0;
    }

    @Override
    public void create(Track entity) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
    }

}
