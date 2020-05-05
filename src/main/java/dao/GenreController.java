package dao;

import entity.Genre;
import entity.Track;

import javax.persistence.EntityManager;
import java.util.List;

public class GenreController extends AbstractController<Genre, Long> {

    @Override
    public List<Genre> getAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Genre> result = em.createQuery("from Genre", Genre.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return result;
    }

    @Override
    public void update(Genre entity) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Genre getEntityById(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Genre result = em.createQuery("from Genre as g where g.id = :name", Genre.class).
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
        List<Track> resultList = em.createQuery("from Track where genre.id=:id", Track.class).
                setParameter("id", id).
                getResultList();
        if (resultList.size() > 0) {
            for (Track tr :
                    resultList) {
                tr.setGenre(null);
                em.merge(tr);
            }
            em.getTransaction().commit();
            em.getTransaction().begin();
        }
        int res = em.createQuery("DELETE Genre WHERE id=:id").
                setParameter("id", id).executeUpdate();
        em.getTransaction().commit();
        em.close();
        return res > 0;
    }

    @Override
    public void create(Genre entity) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
    }

}
