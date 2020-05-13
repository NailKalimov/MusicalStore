package dao;

import entity.Artist;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import java.util.List;

public class ArtistDAO extends AbstractDAO<Artist, Long> {

    public ArtistDAO(EntityManager em) {
        super(em);
    }

    @Override
    public List<Artist> getAll() {
        return em.createQuery("from Artist", Artist.class).getResultList();
    }

    @Override
    public void update(Artist entity) {
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
    public Artist getEntityById(Long id) {
        return em.createQuery("from Artist as g where g.artistId = :id", Artist.class).
                setParameter("id", id).
                getSingleResult();
    }

    @Override
    public boolean deleteById(Long id) {
        em.getTransaction().begin();
        int res = em.createQuery("DELETE Artist WHERE artistId=:id").
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
    public void create(Artist entity) {
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
