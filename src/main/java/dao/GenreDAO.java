package dao;

import entity.Genre;
import entity.Track;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import java.util.List;

public class GenreDAO extends AbstractDAO<Genre, Long> {

    public GenreDAO(EntityManager em) {
        super(em);
    }

    @Override
    public List<Genre> getAll() {
        return em.createQuery("from Genre", Genre.class).getResultList();
    }

    @Override
    public void update(Genre entity) {
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
    public Genre getEntityById(Long id) {
        Genre result = em.createQuery("from Genre as g where g.genreId = :name", Genre.class).
                setParameter("name", id).
                getSingleResult();
        return result;
    }

    @Override
    public boolean deleteById(Long id) {
        em.getTransaction().begin();
        List<Track> resultList = em.createQuery("from Track where genre.genreId=:id", Track.class).
                setParameter("id", id).
                getResultList();
        if (resultList.size() > 0) {
            for (Track tr :
                    resultList) {
                tr.setGenre(null);
                em.merge(tr);
            }
            try {
                em.getTransaction().commit();
            } catch (RollbackException r){
                em.getTransaction().rollback();
                r.printStackTrace();
            }
            em.getTransaction().begin();
        }
        int res = em.createQuery("DELETE Genre WHERE genreId=:id").
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
    public void create(Genre entity) {
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
