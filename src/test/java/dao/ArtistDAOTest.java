package dao;

import UtilityClass.DbInitialState;
import entity.Artist;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ArtistDAOTest {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("entityManager");
    ArtistDAO artistController = new ArtistDAO(entityManagerFactory.createEntityManager());

    @BeforeAll
    public void setUp() {
        DbInitialState.setUp();
    }

    @Test
    public void testGetAllGenre() {
        artistController.getAll().forEach(System.out::println);
    }

    @Test
    public void testUpdateGenre() {
        Artist testArtist = new Artist();
        testArtist.setArtistId(6L);
        testArtist.setArtistName("Updated_Name");
        artistController.update(testArtist);
    }

    @Test
    public void testGetEntityById() {
        System.out.println(artistController.getEntityById(1L));
    }

    @Test
    public void testCreate() {
        Artist newArtist = new Artist();
        newArtist.setArtistName("Created_Artist");
        artistController.create(newArtist);
    }

    @Test
    public void testDeleteById() {
        assertTrue(artistController.deleteById(2L));
    }

}