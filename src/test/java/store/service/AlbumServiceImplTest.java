package store.service;

import TestConfiguration.Config;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import store.entity.Album;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        Config.class
})
@TestPropertySource("classpath:application.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AlbumServiceImplTest {
    @Autowired
    AlbumService albumService;

    @Test
    public void testAddMethodInAlbumService_ifAlbumNameContainsChanson_thenSoutWarningAndDontAddAlbum() {
        Album album = new Album();
        album.setAlbumName("Аля шансончик, чего-кого?");
        albumService.add(album);
    }

}
