package dao;

import UtilityClass.DbInitialState;
import entity.Track;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TrackDAOTest {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("entityManager");
    EntityManager em = entityManagerFactory.createEntityManager();
    TrackDAO trackController = new TrackDAO(em);

    @BeforeAll
    public void setUp() {
        DbInitialState.setUp();
    }

    @Test
    void testGetAll() {
        trackController.getAll().forEach(System.out::println);
    }

    @Test
    void testUpdate() {
        Track updatedTrack = new Track();
        updatedTrack.setTrackName("Колизей");
        updatedTrack.setPlayTime(Duration.ofSeconds(1));
        trackController.update(updatedTrack);
    }

    @Test
    void testGetEntityById() {
        System.out.println(trackController.getEntityById(1L));
    }

    @Test
    void testDeleteById() {
        assertTrue(trackController.deleteById(2L));
    }

    @Test
    void testCreate() {
        Track createdTrack = new Track();
        createdTrack.setTrackName("New_Track");
        trackController.update(createdTrack);
    }
}