package store.dao;
//
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//
//
//public class UserDaoImpl implements UserDao {
//
//    private EntityManagerFactory entityManagerFactory;
//
//    public UserDaoImpl(EntityManagerFactory entityManagerFactory) {
//        this.entityManagerFactory = entityManagerFactory;
//    }
//
//    @Transactional
//    @Override
//    public User getUserById(Integer id) {
//        EntityManager em = entityManagerFactory.createEntityManager();
////        em.getTransaction().begin();
//        User result = (User) em
//                .createQuery("from User as u where u.id=?1", User.class)
//                .setParameter(1, id)
//                .getResultList().get(0);
////        em.getTransaction().commit();
//        em.close();
//        return result;
//    }
//}
