package dao;

import UtilityClass.DbInitialState;
import entity.Genre;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GenreDAOTest {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("entityManager");
    EntityManager em = entityManagerFactory.createEntityManager();
    GenreDAO genreController = new GenreDAO(em);

    @BeforeAll
    public void setUp() {
        DbInitialState.setUp();
    }

    @Test
    public void testGetAllGenre() {
        genreController.getAll().forEach(System.out::println);
    }

    @Test
    public void testUpdateGenre() {
        Genre genreForUpdate = new Genre();
        genreForUpdate.setGenreName("ChangedGenreName");
        genreController.update(genreForUpdate);
    }

    @Test
    public void testGetEntityById() {
        System.out.println("id=1, " + genreController.getEntityById(1L));
    }

    @Test
    public void testCreate() {
        Genre testGenre = new Genre();
        testGenre.setGenreName("Test_Genre");
        genreController.create(testGenre);
    }

    @Test
    public void testDeleteById() {
        assertTrue(genreController.deleteById(2L));
    }

}