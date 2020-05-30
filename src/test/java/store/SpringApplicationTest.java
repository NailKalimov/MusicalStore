package store;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import store.repository.AlbumRepository;
import store.repository.ArtistRepository;
import store.repository.TrackRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SpringApplicationTest {

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void integrationTestForTracks() throws Exception {
        String expectedJson = objectMapper.writeValueAsString(trackRepository.findAll());

        String actualJson = mockMvc.perform(MockMvcRequestBuilders.get("/tracks/all")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertEquals(expectedJson, actualJson);
    }

    @Test
    void integrationTestForAlbums() throws Exception {
        String expectedJson = objectMapper.writeValueAsString(albumRepository.findAll());

        String actualJson = mockMvc.perform(MockMvcRequestBuilders.get("/albums")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertEquals(expectedJson, actualJson);
    }

    @Test
    void integrationTestForArtists() throws Exception {
        String expectedJson = objectMapper.writeValueAsString(artistRepository.findAll());

        String actualJson = mockMvc.perform(MockMvcRequestBuilders.get("/artists")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void testRequestFor_DeleteArtistById_AndExpectResponse() throws Exception {
        Long id = 2L;
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/artists/{id}", id))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testRequestFor_DeleteAlbumById_AndExpectResponse() throws Exception {
        Long id = 2L;
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/albums/{id}", id))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
