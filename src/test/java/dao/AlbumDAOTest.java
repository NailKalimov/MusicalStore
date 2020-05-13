package dao;

import UtilityClass.DbInitialState;
import entity.Album;
import entity.Track;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.Year;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AlbumDAOTest {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("entityManager");
    EntityManager em = entityManagerFactory.createEntityManager();
    AlbumDAO albumController = new AlbumDAO(em);

    @BeforeAll
    public void setUp() {
        DbInitialState.setUp();
    }

    @Test
    public void testGetAllAlbums() {
        albumController.getAll().forEach(System.out::println);
    }

    @Test
    public void testUpdateAlbum() {
        Album testAlbum = new Album();
        testAlbum.setAlbumName("Updated album");
        testAlbum.setAlbumId(4L);
        testAlbum.setReleaseDate(Year.of(2020));
        testAlbum.setTrackList(Collections.singletonList(new Track()));
        albumController.update(testAlbum);
    }

    @Test
    public void testGetEntityById() {
        System.out.println(albumController.getEntityById(1L));
    }

    @Test
    public void testCreate() {
        Album newAlbum = new Album();
        newAlbum.setReleaseDate(Year.of(1234));
        newAlbum.setAlbumName("New_Album");
        albumController.create(newAlbum);
    }

    @Test
    public void testDeleteById() {
        assertTrue(albumController.deleteById(2L));
    }

}