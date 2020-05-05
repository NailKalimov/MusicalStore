package dao;

import entity.Artist;

import javax.persistence.EntityManager;
import java.util.List;

public class ArtistController extends AbstractController<Artist, Long> {

    @Override
    public List<Artist> getAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Artist> result = em.createQuery("from Artist", Artist.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return result;
    }

    @Override
    public void update(Artist entity) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Artist getEntityById(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Artist result = em.createQuery("from Artist as g where g.id = :id", Artist.class).
                setParameter("id", id).
                getSingleResult();
        em.getTransaction().commit();
        em.close();
        return result;
    }

    @Override
    public boolean deleteById(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        int res = em.createQuery("DELETE Artist WHERE id=:id").
                setParameter("id", id).executeUpdate();
        em.getTransaction().commit();
        em.close();
        return res > 0;
    }

    @Override
    public void create(Artist entity) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
    }

}
