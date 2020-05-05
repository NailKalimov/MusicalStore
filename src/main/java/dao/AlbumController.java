package dao;

import entity.Album;

import javax.persistence.EntityManager;
import java.util.List;

public class AlbumController extends AbstractController<Album, Long> {

    @Override
    public List<Album> getAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Album> result = em.createQuery("from Album", Album.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return result;
    }

    @Override
    public void update(Album entity) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Album getEntityById(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Album result = em.createQuery("from Album where id = :id", Album.class).
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
        int res = em.createQuery("DELETE FROM Track where album.id=:id").
                setParameter("id", id).
                executeUpdate();
        if (res > 0) {
            em.getTransaction().commit();
            em.getTransaction().begin();
        }
        res = em.createQuery("DELETE Album WHERE id=:id").
                setParameter("id", id).executeUpdate();
        em.getTransaction().commit();
        em.close();
        return res > 0;
    }

    @Override
    public void create(Album entity) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
    }

}
