package store.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import store.entity.Album;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AlbumRepositoryTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private AlbumRepository albumRepository;

    @Test
    void findByName() {
        Album album = new Album();
        album.setAlbumName("Звезда по имени Солнце");
        album.setReleaseDate(1989);
        em.persist(album);
        em.flush();

        Optional<Album> found = albumRepository.findByAlbumName("Звезда по имени Солнце");

        assertAll(() -> assertTrue(found.isPresent()), () -> assertEquals(album, found.orElse(null)));
    }
}
