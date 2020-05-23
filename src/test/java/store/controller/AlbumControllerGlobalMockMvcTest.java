package store.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import store.entity.Album;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AlbumController.class)
public class AlbumControllerGlobalMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlbumController albumController;

    @Test
    void getAlbums() throws Exception {
        Album album = new Album();
        album.setAlbumName("Гранатовый альбом");
        album.setReleaseDate(1998);
        List<Album> allAlbums = Collections.singletonList(album);

        when(albumController.getAllAlbum()).thenReturn(allAlbums);

        mockMvc.perform(MockMvcRequestBuilders.get("/albums/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].albumName").value("Гранатовый альбом"));
    }
}