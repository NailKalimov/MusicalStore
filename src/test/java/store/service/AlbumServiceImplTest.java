package store.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import store.entity.Album;
import store.repository.AlbumRepo;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)

public class AlbumServiceImplTest {

    @TestConfiguration
    static class Config {
        @Bean
        public AlbumService albumService(AlbumRepo albumRepo) {
            return new AlbumServiceImpl(albumRepo);
        }
    }

    @Autowired
    private AlbumService albumService;

    @MockBean
    private AlbumRepo albumRepo;

    @Test
    void getAll() {
        Album album = new Album();
        album.setAlbumName("Звезда по имени Солнце");
        album.setReleaseDate(1989);
        List<Album> expectedList = Collections.singletonList(album);
        when(albumRepo.findAll()).thenReturn(expectedList);

        List<Album> actualList = albumService.getAllAlbumsReleaseDateBefore(2000);

        assertIterableEquals(expectedList, actualList);
    }
}
